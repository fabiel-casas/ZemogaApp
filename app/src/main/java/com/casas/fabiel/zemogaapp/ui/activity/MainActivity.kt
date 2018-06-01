package com.casas.fabiel.zemogaapp.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.OvershootInterpolator
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.ui.adapters.PostsTabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var postTabAdapter: PostsTabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMainActivity)
        postTabAdapter = PostsTabsAdapter(supportFragmentManager)
        viewPagerPosts.adapter = postTabAdapter
        tabLayoutPosts.setupWithViewPager(viewPagerPosts)
        createCustomAnimation()
        setMenuButtonsListeners()
    }

    private fun setMenuButtonsListeners() {
        fabOk.setOnClickListener({
            deleteAll()
            fabMenu.close(true)
        })
        fabCancel.setOnClickListener({
            fabMenu.close(true)
        })
    }

    private fun deleteAll() {
        postTabAdapter.deleteAllPost()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_refresh, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menu_item_refresh -> updatePostList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updatePostList() {

    }

    private fun createCustomAnimation() {
        fabMenu.menuIconView.setImageResource(R.drawable.ic_delete)
        val set = AnimatorSet()

        val scaleOutX = ObjectAnimator.ofFloat(fabMenu.menuIconView, "scaleX", 1.0f, 0.2f)
        val scaleOutY = ObjectAnimator.ofFloat(fabMenu.menuIconView, "scaleY", 1.0f, 0.2f)

        val scaleInX = ObjectAnimator.ofFloat(fabMenu.menuIconView, "scaleX", 0.2f, 1.0f)
        val scaleInY = ObjectAnimator.ofFloat(fabMenu.menuIconView, "scaleY", 0.2f, 1.0f)

        scaleOutX.duration = 50
        scaleOutY.duration = 50

        scaleInX.duration = 150
        scaleInY.duration = 150

        scaleInX.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                fabMenu.menuIconView.setImageResource(if (fabMenu.isOpened)
                    R.drawable.ic_delete
                else
                    R.drawable.ic_no)
            }
        })

        set.play(scaleOutX).with(scaleOutY)
        set.play(scaleInX).with(scaleInY).after(scaleOutX)
        set.interpolator = OvershootInterpolator(2f)

        fabMenu.iconToggleAnimatorSet = set
    }
}

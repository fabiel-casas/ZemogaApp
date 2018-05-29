package com.casas.fabiel.zemogaapp.ui.activity

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.ui.adapters.PostsTabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMainActivity)
        viewPagerPosts.adapter = PostsTabsAdapter(supportFragmentManager)
        tabLayoutPosts.setupWithViewPager(viewPagerPosts)
    }
}

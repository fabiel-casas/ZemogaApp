package com.casas.fabiel.zemogaapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.data.repository.entities.User
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.ui.adapters.CommentsAdapter
import com.casas.fabiel.zemogaapp.ui.view.SimpleDividerItemDecoration
import com.casas.fabiel.zemogaapp.utils.StringsUtils
import com.casas.fabiel.zemogaapp.viewmodel.PostDetailViewModel
import kotlinx.android.synthetic.main.activity_post_detail.*


class PostDetailActivity : AppCompatActivity() {

    companion object {
        var KEY_POST = "posts_item"
    }

    private var postData: Posts? = null
    private lateinit var user: User
    private lateinit var viewModel: PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setSupportActionBar(toolbarDetailPost)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        postData = intent.extras.getSerializable(KEY_POST) as Posts
        user = User.getDefaultUser()
        viewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
        viewModel.intRepository(this, postData)
        viewModel.getComments(this, postData).observe(this, Observer{
            updateComments(it)
        })
        populateData()
    }

    private fun updateComments(comments: List<Comments>?) {
        recyclerViewComments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var adapter = CommentsAdapter()
        adapter.commentsList = comments!!
        recyclerViewComments.addItemDecoration(SimpleDividerItemDecoration(this))
        recyclerViewComments.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menu_item_favorite -> updatePostFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updatePostFavorite() {
        viewModel.updateModel(postData!!)
    }

    private fun populateData() {
        textViewDetailDescription.text = postData!!.body
        textViewName.text = getString(R.string.post_detail_user_name, StringsUtils.capitalizeText(user.name))
        textViewEmail.text = getString(R.string.post_detail_user_email, user.email)
        textViewPhone.text = getString(R.string.post_detail_user_phone, StringsUtils.capitalizeFirstLetter(user.phone))
        textViewWebsite.text = getString(R.string.post_detail_user_website, user.webSite)
    }
}

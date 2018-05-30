package com.casas.fabiel.zemogaapp.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.ui.activity.PostDetailActivity
import com.casas.fabiel.zemogaapp.ui.adapters.PostAdapter
import com.casas.fabiel.zemogaapp.ui.view.SimpleDividerItemDecoration
import com.casas.fabiel.zemogaapp.viewmodel.PostsListViewModel
import kotlinx.android.synthetic.main.fragment_posts_list.*

class PostsListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_posts_list, container, false)
        return view
    }

    private lateinit var viewModel: PostsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)
                .get(PostsListViewModel::class.java)
        viewModel.getPostList(context!!).observe(this, Observer {
            updateAdapterView(it)
        })
    }

    private fun updateAdapterView(posts: List<Posts>?) {
        recyclerViewPostList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewPostList.addItemDecoration(SimpleDividerItemDecoration(context!!))
        val adapter = PostAdapter()
        adapter.setPostItemListener({
            showPostsDetail(it)
        })
        adapter.postsLists = posts!!
        recyclerViewPostList.adapter = adapter
    }

    private fun showPostsDetail(posts: Posts) {
        val intent = Intent(context, PostDetailActivity::class.java)
        intent.putExtra(PostDetailActivity.KEY_POST, posts)
        startActivity(intent)
    }

}

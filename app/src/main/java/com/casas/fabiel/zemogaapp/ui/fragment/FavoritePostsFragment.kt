package com.casas.fabiel.zemogaapp.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import com.casas.fabiel.zemogaapp.viewmodel.PostsFavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorite_posts.*
import kotlinx.android.synthetic.main.fragment_posts_list.*


class FavoritePostsFragment : Fragment() {

    private lateinit var viewModel: PostsFavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_posts, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)
                .get(PostsFavoritesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPostFavoriteList(context!!).observe(this, Observer {
            updateAdapter(it)
        })
    }

    private fun updateAdapter(posts: List<Posts>?) {
        recyclerViewFavoritePosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewFavoritePosts.addItemDecoration(SimpleDividerItemDecoration(context!!))
        val adapter = PostAdapter()
        adapter.setPostItemListener({
            showPostsDetail(it)
        })
        adapter.postsLists = posts!!
        recyclerViewFavoritePosts.adapter = adapter
    }

    private fun showPostsDetail(post: Posts) {
        val intent = Intent(context, PostDetailActivity::class.java)
        intent.putExtra(PostDetailActivity.KEY_POST, post)
        startActivity(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}

package com.casas.fabiel.zemogaapp.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.viewmodel.PostsListViewModel

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
        viewModel.getPostList(context!!).observe(this, updateAdapterView())
    }

    fun updateAdapterView() : Observer<List<Posts>> {
        return Observer {

        }
    }

}

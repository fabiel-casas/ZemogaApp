package com.casas.fabiel.zemogaapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.livedata.PostsListener

class PostsListViewModel : ViewModel() {

    private lateinit var postListener: PostsListener

    fun getPostList(context: Context): LiveData<List<Posts>> {
        if (postListener == null) {
            postListener = PostsListener(context)
        }
        return postListener
    }
}
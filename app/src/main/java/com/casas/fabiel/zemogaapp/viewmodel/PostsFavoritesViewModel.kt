package com.casas.fabiel.zemogaapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.livedata.PostsFavoritesListener
import com.casas.fabiel.zemogaapp.livedata.PostsListener

class PostsFavoritesViewModel : ViewModel() {

    private var postListener: PostsFavoritesListener? = null

    fun getPostFavoriteList(context: Context): LiveData<List<Posts>> {
        if (postListener == null) {
            postListener = PostsFavoritesListener(context)
        }
        return postListener!!
    }
}
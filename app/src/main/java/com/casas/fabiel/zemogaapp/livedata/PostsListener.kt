package com.casas.fabiel.zemogaapp.livedata

import android.arch.lifecycle.LiveData
import android.content.Context
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.data.services.PostsListsService

class PostsListener(var context: Context) : LiveData<List<Posts>>() {

    private lateinit var postsService: PostsListsService
    private val listener: (List<Posts>) -> Unit = {
        value = it
    }

    init {
        postsService = PostsListsService(context)
    }

    override fun onActive() {
        super.onActive()
        postsService.getAllPost(context, listener)
    }

    override fun onInactive() {
        super.onInactive()
    }

    private fun connect() {

    }

}
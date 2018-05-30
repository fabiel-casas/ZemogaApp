package com.casas.fabiel.zemogaapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.casas.fabiel.data.repository.PostsListRepository
import com.casas.fabiel.data.repository.entities.Posts

class PostDetailViewModel : ViewModel() {

    private lateinit var postsRepository: PostsListRepository

    fun intRepository(context: Context) {
        postsRepository = PostsListRepository(context)
    }

    fun updateModel(posts: Posts) {
        posts.isFavorite = !posts.isFavorite
        postsRepository.updateOnePosts(posts)!!.subscribe()
    }

}
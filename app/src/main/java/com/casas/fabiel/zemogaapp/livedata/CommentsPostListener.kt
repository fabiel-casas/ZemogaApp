package com.casas.fabiel.zemogaapp.livedata

import android.arch.lifecycle.LiveData
import android.content.Context
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.data.services.PostsListsService

class CommentsPostListener(var context: Context, var postId:String): LiveData<List<Comments>>() {

    private lateinit var postsService: PostsListsService
    private val listener: (List<Comments>) -> Unit = {
        value = it
    }

    init {
        postsService = PostsListsService(context)
    }

    override fun onActive() {
        super.onActive()
        postsService.getCommentsInAPost(postId, listener)
    }

    override fun onInactive() {
        super.onInactive()
    }

}
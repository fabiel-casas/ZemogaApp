package com.casas.fabiel.zemogaapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.casas.fabiel.data.repository.PostsListRepository
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.livedata.CommentsPostListener

class PostDetailViewModel : ViewModel() {

    private lateinit var postsRepository: PostsListRepository
    private var commentsListener: CommentsPostListener? = null

    fun intRepository(context: Context, postData: Posts?) {
        postsRepository = PostsListRepository(context)
        postsRepository.increasePostViews(postData!!.id).subscribe()
    }

    fun updateModel(posts: Posts) {
        posts.isFavorite = !posts.isFavorite
        postsRepository.updateOnePosts(posts)!!.subscribe()
    }

    fun getComments(context: Context, postData: Posts?): LiveData<List<Comments>> {
        if (commentsListener == null) {
            commentsListener = CommentsPostListener(context, postData!!.id.toString())
        }
        return commentsListener!!
    }

}
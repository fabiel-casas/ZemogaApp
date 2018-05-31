package com.casas.fabiel.data.services

import android.content.Context
import com.casas.fabiel.data.repository.PostsListRepository
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.data.repository.entities.Posts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListsService(val context: Context) {

    private lateinit var postsRequest: PostRequest
    private lateinit var postsRepository: PostsListRepository

    init {
        postsRequest = PostRequest.create()
        postsRepository = PostsListRepository(context)
    }

    fun getAllPost(listener: (List<Posts>) -> Unit) {
        Observable.create<List<Posts>> {
            var postList = postsRepository.getAllPost()
            if (postList.size > 0) {
                it.onNext(postList)
            } else {
                var response = postsRequest.getAllPosts().execute()
                if (response.isSuccessful) {
                    it.onNext(response.body())
                } else {
                    it.onError(Throwable())
                }
            }
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    postsRepository.savePosts(it)!!.subscribe()
                    listener(it)
                }, {
                    postsRepository.getAllPost(listener)
                })
    }

    fun getAllPostFavorite(listener: (List<Posts>) -> Unit) {
        postsRepository.getAllFavoritePost(listener)
    }

    fun getCommentsInAPost(postId: String, listener: (List<Comments>) -> Unit) {
        postsRequest.getAllCommentsInAPosts(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    listener(it)
                }, {
                    listener(arrayListOf())
                })
    }

}
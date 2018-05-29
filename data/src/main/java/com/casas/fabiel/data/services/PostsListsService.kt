package com.casas.fabiel.data.services

import android.content.Context
import com.casas.fabiel.data.repository.PostsListRepository
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

    fun getAllPost(context: Context, listener: (List<Posts>) -> Unit) {
        Observable.create<List<Posts>> {
            var response = postsRequest.getAllPosts().execute()
            if (response.isSuccessful) {
                it.onNext(response.body())
            } else {
                it.onError(Throwable())
            }
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    postsRepository.savePosts(it)
                    listener(it)
                }, {
                    postsRepository.getAllPost(listener)
                })
    }

}
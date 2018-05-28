package com.casas.fabiel.data.services

import android.content.Context
import com.casas.fabiel.data.repository.database.ZemogaDatabase
import com.casas.fabiel.data.repository.entities.Posts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListsService(val context: Context) {

    private lateinit var postsRequest: PostRequest
    private var database: ZemogaDatabase

    init {
        postsRequest = PostRequest.create()
        database = ZemogaDatabase.getInstance(context);
    }

    fun getAllPost(context: Context, listener: (List<Posts>) -> Unit) {
        postsRequest.getAllPosts()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    database.postsDao().insertAll(it)
                    listener(it)
                }, {
                    listener(database.postsDao().getAll())
                })
    }

}
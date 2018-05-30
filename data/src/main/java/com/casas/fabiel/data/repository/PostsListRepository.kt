package com.casas.fabiel.data.repository

import android.content.Context
import com.casas.fabiel.data.repository.database.ZemogaDatabase
import com.casas.fabiel.data.repository.entities.Posts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListRepository {

    private var database: ZemogaDatabase

    constructor(context: Context) {
        database = ZemogaDatabase.getInstance(context)
    }

    fun updateOnePosts(posts: Posts): Observable<Posts>? {
        return Observable.create<Posts> {
            var selected = if (posts.isFavorite) {
                1
            } else {
                0
            }
            database.postsDao().updatePost(selected, postId = posts.id)
            it.onNext(posts)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun savePosts(posts: List<Posts>): Observable<List<Posts>>? {
        return Observable.create<List<Posts>> {
            database.postsDao().insertAll(posts)
            it.onNext(posts)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllPost(listener: (List<Posts>) -> Unit) {
        Observable.create<List<Posts>> {
            it.onNext(database.postsDao().getAll())
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener(it)
                }, {
                    listener(arrayListOf())
                })
    }

    fun getAllPost(): List<Posts> {
        return database.postsDao().getAll()
    }

    fun getAllFavoritePost(listener: (List<Posts>) -> Unit) {
        Observable.create<List<Posts>> {
            it.onNext(database.postsDao().loadAllFavorites())
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener(it)
                }, {
                    listener(arrayListOf())
                })
    }
}
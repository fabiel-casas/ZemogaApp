package com.casas.fabiel.data.repository

import android.content.Context
import com.casas.fabiel.data.repository.database.ZemogaDatabase
import com.casas.fabiel.data.repository.entities.Posts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListRepository {

    private var database: ZemogaDatabase

    constructor(context:Context) {
        database = ZemogaDatabase.getInstance(context)
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
                .subscribe( {
                    listener(it)
                }, {
                    listener(arrayListOf())
                })
    }
}
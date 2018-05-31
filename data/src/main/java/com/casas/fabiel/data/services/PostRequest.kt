package com.casas.fabiel.data.services

import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.data.repository.entities.Posts
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PostRequest {
    @GET("/posts")
    fun getAllPosts(): Call<List<Posts>>

    @GET("/comments")
    fun getAllCommentsInAPosts(@Query("postId") postId: String): Observable<List<Comments>>

    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun create(): PostRequest {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(PostRequest::class.java)
        }
    }
}

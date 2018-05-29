package com.casas.fabiel.data.services

import com.casas.fabiel.data.repository.entities.Posts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostRequest {
    @GET("/posts")
    fun getAllPosts(): Call<List<Posts>>

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

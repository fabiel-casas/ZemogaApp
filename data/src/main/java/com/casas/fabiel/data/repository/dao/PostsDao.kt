package com.casas.fabiel.data.repository.dao

import android.arch.persistence.room.*
import com.casas.fabiel.data.repository.entities.Posts


@Dao
interface PostsDao {
    @Query("SELECT * from Posts")
    fun getAll(): List<Posts>

    @Query("SELECT * FROM Posts WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Posts>

    @Query("SELECT * FROM Posts WHERE isFavorite = 1")
    fun loadAllFavorites(): List<Posts>

    @Query("UPDATE Posts SET isFavorite = (:selected) WHERE id = (:postId)")
    fun updatePost(selected: Int, postId:Int)

    @Query("UPDATE Posts SET viewCount = viewCount + 1 WHERE id = (:postId)")
    fun updatePost(postId:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(posts: List<Posts>)

    @Query("DELETE from Posts")
    fun deleteAll()

    @Delete
    fun delete(posts: Posts)
}
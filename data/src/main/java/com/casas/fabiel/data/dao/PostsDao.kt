package com.casas.fabiel.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.casas.fabiel.data.entities.Posts


@Dao
interface PostsDao {
    @Query("SELECT * from Posts")
    fun getAll(): List<Posts>

    @Query("SELECT * FROM Posts WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Posts>

    @Insert
    fun insert(posts: Posts)

    @Insert
    fun insertAll(vararg posts: Posts)

    @Query("DELETE from Posts")
    fun deleteAll()

    @Delete
    fun delete(posts: Posts)
}
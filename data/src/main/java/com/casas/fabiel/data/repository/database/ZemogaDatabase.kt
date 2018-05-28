package com.casas.fabiel.data.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.casas.fabiel.data.repository.dao.PostsDao
import com.casas.fabiel.data.repository.entities.Posts

@Database(entities = arrayOf(Posts::class), version = 1)
abstract class ZemogaDatabase : RoomDatabase() {
    companion object {
        var INSTANCE: ZemogaDatabase? = null
        val ZEMOGA_DB: String = "zemoga.db"

        fun getInstance(context: Context): ZemogaDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, ZemogaDatabase::class.java, ZEMOGA_DB).build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun postsDao(): PostsDao
}
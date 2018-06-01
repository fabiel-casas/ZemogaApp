package com.casas.fabiel.data.repository.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Posts")
data class Posts(@PrimaryKey var id : Int,
                 var userId: String,
                 var title: String,
                 var body : String,
                 var viewCount:Int = 0,
                 var isFavorite:Boolean = false
): Serializable
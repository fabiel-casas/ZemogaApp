package com.casas.fabiel.data.repository.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Posts")
data class Posts(@PrimaryKey var id : Int,
                 var userId: String,
                 var title: String,
                 var body : String)
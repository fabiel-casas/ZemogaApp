package com.casas.fabiel.data.repository.entities

import java.io.Serializable

data class Comments(var id: Int,
                    var postId: String,
                    var name: String,
                    var body: String,
                    var email: String) : Serializable
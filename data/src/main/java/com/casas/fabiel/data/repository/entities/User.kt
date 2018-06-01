package com.casas.fabiel.data.repository.entities

data class User(var name: String,
                var email: String,
                var phone: String,
                var webSite: String) {
    companion object {
        fun getDefaultUser(): User {
            return User("Fabiel Casas",
                    "fabiel016@gmail.com",
                    "3156479399",
                    "www.fabiel.com")
        }
    }
}
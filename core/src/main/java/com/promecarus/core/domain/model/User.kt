@file:Suppress("PropertyName")

package com.promecarus.core.domain.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val type: String,
)

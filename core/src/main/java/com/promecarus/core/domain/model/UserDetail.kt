@file:Suppress("PropertyName")

package com.promecarus.core.domain.model

data class UserDetail(
    val login: String = "",
    val id: Int = 0,
    val avatar_url: String = "",
    val type: String = "",
    val name: String? = null,
    val company: String? = null,
    val location: String? = null,
    val bio: String? = null,
    val public_repos: Int = 0,
    val public_gists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
)

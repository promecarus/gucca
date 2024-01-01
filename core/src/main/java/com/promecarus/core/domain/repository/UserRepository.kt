package com.promecarus.core.domain.repository

import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun search(
        query: String,
        perPage: Int,
    ): Flow<Search>

    fun getDetail(login: String): Flow<UserDetail>

    fun getFollowers(
        token: String,
        username: String,
        perPage: Int,
    ): Flow<List<User>>

    fun getFollowing(
        token: String,
        username: String,
        perPage: Int,
    ): Flow<List<User>>
}

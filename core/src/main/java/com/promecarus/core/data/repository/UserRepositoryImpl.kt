package com.promecarus.core.data.repository

import com.promecarus.core.data.remote.ApiService
import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override fun search(
        query: String,
        perPage: Int,
    ): Flow<Search> = flow {
        emit(apiService.search(query, perPage))
    }

    override fun getDetail(login: String): Flow<UserDetail> = flow {
        emit(apiService.getDetail(login))
    }

    override fun getFollowers(
        token: String,
        username: String,
        perPage: Int,
    ): Flow<List<User>> = flow {
        emit(
            apiService.getFollowers(
                token = token,
                username = username,
                perPage = perPage,
            )
        )
    }

    override fun getFollowing(
        token: String,
        username: String,
        perPage: Int,
    ): Flow<List<User>> = flow {
        emit(
            apiService.getFollowing(
                token = token,
                username = username,
                perPage = perPage,
            )
        )
    }
}

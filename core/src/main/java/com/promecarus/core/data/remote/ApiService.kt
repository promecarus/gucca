package com.promecarus.core.data.remote

import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
    ): Search

    @GET("users/{username}")
    suspend fun getDetail(@Path("username") username: String): UserDetail

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
    ): List<User>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
    ): List<User>
}

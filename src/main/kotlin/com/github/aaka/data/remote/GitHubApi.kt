package com.github.aaka.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitHubApi {

    @GET("repos/{username}/{repo}")
    suspend fun getRepo(
        @Header("Authorization") authToken: String,
        @Path("username") username: String,
        @Path("repo") repo: String,
        @Header("Accept") accept: String = "application/vnd.github.v3+json"
    ): GetRepoResponse
}
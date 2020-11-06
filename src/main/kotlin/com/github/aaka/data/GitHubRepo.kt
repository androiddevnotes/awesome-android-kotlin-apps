package com.github.aaka.data

import com.github.aaka.data.remote.GitHubApi
import com.github.aaka.data.repo.GetRepoResponse
import javax.inject.Inject

class GitHubRepo @Inject constructor(
    val gitHubApi: GitHubApi
) {

    suspend fun getRepo(username: String, repoName: String): GetRepoResponse {
        val githubToken = System.getenv("MOVIE_MONK_GITHUB_ACCESS_TOKEN")
        return gitHubApi.getRepo(
            authToken = "token $githubToken",
            username = username,
            repo = repoName
        )
    }
}
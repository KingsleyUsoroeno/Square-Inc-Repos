package com.example.squareincrepositories.features.github_repos.data.remote

import com.example.squareincrepositories.features.github_repos.data.remote.model.GithubRepoDto
import retrofit2.http.GET

interface GithubApiService {

    @GET("orgs/square/repos")
    suspend fun fetchGithubRepos(): List<GithubRepoDto>
}
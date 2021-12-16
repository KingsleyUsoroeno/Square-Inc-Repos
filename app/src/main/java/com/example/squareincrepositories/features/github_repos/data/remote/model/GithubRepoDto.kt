package com.example.squareincrepositories.features.github_repos.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    val id: Int,
    @SerializedName("name")
    val repoName: String,
    @SerializedName("stargazers_count")
    val starCount: Int
)
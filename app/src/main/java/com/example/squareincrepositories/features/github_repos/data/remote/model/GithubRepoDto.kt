package com.example.squareincrepositories.features.github_repos.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count")
    val starCount: Int
)
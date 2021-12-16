package com.example.squareincrepositories.features.github_repos.domain.model

data class GithubRepo(
    val id: Int,
    val name: String,
    val starsCount: Int,
    val isBookmarked: Boolean,
)
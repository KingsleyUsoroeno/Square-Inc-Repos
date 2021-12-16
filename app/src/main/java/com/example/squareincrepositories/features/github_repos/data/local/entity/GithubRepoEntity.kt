package com.example.squareincrepositories.features.github_repos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepoEntity(
    @PrimaryKey val id: Int,
    val repoName: String,
    val starsCount: Int,
    val isBookmarked: Boolean = false
)
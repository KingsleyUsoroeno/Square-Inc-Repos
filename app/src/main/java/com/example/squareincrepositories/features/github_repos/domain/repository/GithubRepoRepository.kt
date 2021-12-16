package com.example.squareincrepositories.features.github_repos.domain.repository

import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepoRepository {

    fun fetchGithubRepos(): Flow<List<GithubRepo>>

    suspend fun addToBookmark(id: Int)

    suspend fun findRepositoryById(id: Int): GithubRepo?

    suspend fun insertGithubRepos(githubRepos: List<GithubRepo>)

    suspend fun removeBookmark(id: Int)
}
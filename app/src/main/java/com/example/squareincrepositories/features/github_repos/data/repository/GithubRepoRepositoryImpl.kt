package com.example.squareincrepositories.features.github_repos.data.repository

import com.example.squareincrepositories.features.github_repos.data.local.dao.GithubRepoDao
import com.example.squareincrepositories.features.github_repos.data.local.mapper.GithubRepoEntityMapper
import com.example.squareincrepositories.features.github_repos.data.remote.GithubApiService
import com.example.squareincrepositories.features.github_repos.data.remote.mapper.GithubRepoDtoMapper
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import com.example.squareincrepositories.features.github_repos.domain.repository.GithubRepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val githubRepoDao: GithubRepoDao,
    private val githubRepoEntityMapper: GithubRepoEntityMapper = GithubRepoEntityMapper(),
    private val githubRepoDtoMapper: GithubRepoDtoMapper = GithubRepoDtoMapper()
) : GithubRepoRepository {

    override fun fetchGithubRepos(): Flow<List<GithubRepo>> = flow {
        val cachedGithubRepoEntities = githubRepoDao.getGithubRepos()
        if (cachedGithubRepoEntities.isEmpty()) {
            val githubRepos = githubApiService.fetchGithubRepos()
            val githubRepositories = githubRepoDtoMapper.mapModelList(githubRepos)
            insertGithubRepos(githubRepos = githubRepositories)
            emit(githubRepoEntityMapper.mapToModelList(githubRepoDao.getGithubRepos()))
        } else {
            emit(githubRepoEntityMapper.mapToModelList(cachedGithubRepoEntities))
        }
    }

    override suspend fun addToBookmark(id: Int) {
        githubRepoDao.updateBookmarkStatus(id, isBookmarked = true)
    }

    override suspend fun findRepositoryById(id: Int): GithubRepo? {
        val githubEntity = githubRepoDao.findGithubRepoById(id = id)
        return githubEntity?.let { githubRepoEntityMapper.mapToModel(it) }
    }

    override suspend fun insertGithubRepos(githubRepos: List<GithubRepo>) {
        val githubRepoEntities = githubRepoEntityMapper.mapToEntityList(githubRepos)
        githubRepoDao.insertGithubRepos(githubRepoEntities)
    }

    override suspend fun removeBookmark(id: Int) {
        githubRepoDao.updateBookmarkStatus(id = id, isBookmarked = false)
    }
}
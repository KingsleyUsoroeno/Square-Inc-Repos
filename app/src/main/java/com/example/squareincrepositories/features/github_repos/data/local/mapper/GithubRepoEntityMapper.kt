package com.example.squareincrepositories.features.github_repos.data.local.mapper

import com.example.squareincrepositories.features.github_repos.data.local.entity.GithubRepoEntity
import com.example.squareincrepositories.features.github_repos.data.local.mapper.base.CacheEntityMapper
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import javax.inject.Inject

class GithubRepoEntityMapper @Inject constructor() :
    CacheEntityMapper<GithubRepo, GithubRepoEntity> {
    override fun mapToModel(entity: GithubRepoEntity): GithubRepo {
        return GithubRepo(
            id = entity.id,
            name = entity.repoName,
            starsCount = entity.starsCount,
            isBookmarked = entity.isBookmarked,
        )
    }

    override fun mapToEntity(model: GithubRepo): GithubRepoEntity {
        return GithubRepoEntity(
            id = model.id,
            repoName = model.name,
            starsCount = model.starsCount,
            isBookmarked = model.isBookmarked,
        )
    }
}
package com.example.squareincrepositories.features.github_repos.data.remote.mapper

import com.example.squareincrepositories.features.github_repos.data.remote.mapper.base.RemoteModelMapper
import com.example.squareincrepositories.features.github_repos.data.remote.model.GithubRepoDto
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import javax.inject.Inject

class GithubRepoDtoMapper @Inject constructor() :
    RemoteModelMapper<GithubRepoDto, GithubRepo> {

    override fun mapFromModel(model: GithubRepoDto): GithubRepo {
        return GithubRepo(
            id = model.id,
            name = model.name,
            starsCount = model.starCount,
            isBookmarked = false,
        )
    }
}
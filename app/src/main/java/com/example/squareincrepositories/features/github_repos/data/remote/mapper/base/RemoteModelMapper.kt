package com.example.squareincrepositories.features.github_repos.data.remote.mapper.base

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

    fun mapModelList(models: List<M>): List<E> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}
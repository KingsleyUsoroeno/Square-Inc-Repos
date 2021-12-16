package com.example.squareincrepositories.features.github_repos.presentation.github_repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import com.example.squareincrepositories.features.github_repos.domain.repository.GithubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val githubRepository: GithubRepoRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun addToBookmarks(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.addToBookmark(id)
            emitSnackBarUiEvent("Successfully Added to bookmarks")
        }
    }

    fun findRepositoryById(id: Int): Flow<GithubRepo?> {
        return flow {
            val githubRepo = withContext(Dispatchers.IO) {
                githubRepository.findRepositoryById(id)
            }
            emit(githubRepo)
        }
    }

    fun removeBookmark(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.removeBookmark(id)
            emitSnackBarUiEvent("Successfully removed from bookmarks")
        }
    }

    private suspend fun emitSnackBarUiEvent(message: String) {
        _eventFlow.emit(UiEvent.ShowSnackBar(message = message))
    }
}

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}
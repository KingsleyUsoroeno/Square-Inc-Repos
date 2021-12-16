package com.example.squareincrepositories.features.github_repos.presentation.repo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import com.example.squareincrepositories.features.github_repos.domain.repository.GithubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryScreenViewModel @Inject constructor(
    private val githubRepository: GithubRepoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState get() = _uiState.asStateFlow()

    fun fetchGithubRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            githubRepository.fetchGithubRepos()
                .onEach { _uiState.value = UiState.Loaded(it) }
                .catch {
                    val errorMessage = it.message ?: "Something went wrong, please try again later"
                    _uiState.value = UiState.Error(errorMessage = errorMessage)
                }.launchIn(this)
        }
    }
}

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Loaded(val githubRepos: List<GithubRepo>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}
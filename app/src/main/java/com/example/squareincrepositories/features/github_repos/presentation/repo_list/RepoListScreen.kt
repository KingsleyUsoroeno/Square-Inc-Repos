package com.example.squareincrepositories.features.github_repos.presentation.repo_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.squareincrepositories.features.github_repos.presentation.Screen.RepositoryDetailScreen
import com.example.squareincrepositories.features.github_repos.presentation.components.GithubRepoItem
import com.example.squareincrepositories.features.github_repos.presentation.components.LoadingShimmer

@Composable
fun RepoListScreen(
    viewModel: RepositoryScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = "Github Repositories", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }) {

        LaunchedEffect(key1 = true) {
            viewModel.fetchGithubRepos()
        }
        val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)

        when (uiState) {
            is UiState.Idle -> {
            }

            is UiState.Loading -> {
                LoadingShimmer()
            }

            is UiState.Loaded -> {
                val repositories = (uiState as UiState.Loaded).githubRepos
                LazyColumn(modifier = Modifier.padding(it)) {
                    items(repositories.size, itemContent = { index ->
                        GithubRepoItem(githubRepo = repositories[index]) { repoId ->
                            navController.navigate(RepositoryDetailScreen.withArgs(repoId))
                        }
                    })
                }
            }

            is UiState.Error -> {

            }
        }
    }
}
package com.example.squareincrepositories.features.github_repos.presentation.repo_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.squareincrepositories.R
import com.example.squareincrepositories.features.github_repos.presentation.Screen.RepositoryDetailScreen
import com.example.squareincrepositories.features.github_repos.presentation.components.GithubRepoListItem
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

        LaunchedEffect(key1 = true) { viewModel.fetchGithubRepos() }

        val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)

        when (uiState) {
            is UiState.Idle -> {
            }

            is UiState.Loading -> {
                LoadingShimmer()
            }

            is UiState.Loaded -> {
                GithubRepoListItem(
                    modifier = Modifier.padding(it),
                    githubRepos = (uiState as UiState.Loaded).githubRepos,
                    onItemClicked = { repoId ->
                        navController.navigate(RepositoryDetailScreen.withArgs(repoId))
                    },
                )
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).errorMessage
                ErrorStateComposable(errorMessage = errorMessage)
            }
        }
    }
}

@Composable
fun ErrorStateComposable(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.img_error), contentDescription = "error image")
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = errorMessage, textAlign = TextAlign.Center)
    }
}
package com.example.squareincrepositories.features.github_repos.presentation.github_repo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo
import com.example.squareincrepositories.features.github_repos.presentation.components.RowTextComposable
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RepositoryDetailScreen(
    navController: NavController,
    viewModel: GithubRepoViewModel = hiltViewModel(),
    id: Int?
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        id?.let {
            val githubRepo by viewModel.findRepositoryById(it).collectAsState(initial = null)
            if (githubRepo == null) {
                Text(text = "Repository does not exist")
            } else {
                RepoScreenDetailItem(
                    githubRepo = githubRepo!!,
                    onAddBookmark = {
                        viewModel.addToBookmarks(githubRepo!!.id)
                    },
                    onRemoveBookmark = {
                        viewModel.removeBookmark(id = githubRepo!!.id)
                    }
                )
            }
        }
    }
}

@Composable
fun RepoScreenDetailItem(
    githubRepo: GithubRepo,
    onAddBookmark: () -> Unit,
    onRemoveBookmark: () -> Unit
) {

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        RowTextComposable(title = "Repository Name", desc = githubRepo.name)
        Spacer(modifier = Modifier.height(6.dp))
        RowTextComposable(title = "Stars Count", desc = githubRepo.starsCount.toString())

        if (githubRepo.isBookmarked) {
            Button(
                onClick = onRemoveBookmark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Remove Bookmark")
            }
        } else {
            Button(
                onClick = onAddBookmark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Add to bookmarks")
            }
        }
    }
}

@Preview
@Composable
fun RepositoryDetailScreenPreview() {
    val githubRepo = GithubRepo(
        id = 3, name = "Star wars",
        isBookmarked = true,
        starsCount = 5
    )
    RepoScreenDetailItem(
        githubRepo = githubRepo,
        onAddBookmark = {},
        onRemoveBookmark = {}
    )
}
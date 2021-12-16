package com.example.squareincrepositories.features.github_repos.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo

@Composable
fun GithubRepoListItem(
    repositories: List<GithubRepo>,
    modifier: Modifier = Modifier,
    onItemClicked: (id: Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(repositories.size, itemContent = { index ->
            GithubRepoItem(githubRepo = repositories[index]) { repoId ->
                onItemClicked(repoId)
            }
        })
    }
}
package com.example.squareincrepositories.features.github_repos.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.squareincrepositories.features.github_repos.domain.model.GithubRepo

@Composable
fun GithubRepoItem(
    modifier: Modifier = Modifier,
    githubRepo: GithubRepo,
    onItemClicked: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                top = 12.dp,
                bottom = 12.dp, start = 20.dp, end = 20.dp,
            )
            .clickable { onItemClicked(githubRepo.id) }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "Repository Name")
                if (githubRepo.isBookmarked) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        Icons.Filled.Favorite, contentDescription = "favourite icon",
                        tint = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                }
            }
            Text(text = githubRepo.name)
        }

        RowTextComposable(title = "Stars Count", desc = githubRepo.starsCount.toString())
    }
}

@Composable
fun RowTextComposable(title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(
            text = desc, style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
        )
    }
}

@Preview
@Composable
fun GithubRepoCardPreview() {
    val githubRepo = GithubRepo(id = 1, name = "True story", starsCount = 12, isBookmarked = true)
    GithubRepoItem(githubRepo = githubRepo) {}
}
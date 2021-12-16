package com.example.squareincrepositories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.squareincrepositories.features.github_repos.presentation.Screen.RepositoryDetailScreen
import com.example.squareincrepositories.features.github_repos.presentation.Screen.RepositoryScreen
import com.example.squareincrepositories.features.github_repos.presentation.github_repo.RepositoryDetailScreen
import com.example.squareincrepositories.features.github_repos.presentation.repo_list.RepoListScreen
import com.example.squareincrepositories.ui.theme.SquareIncRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareIncRepositoriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = RepositoryScreen.routeName
                    ) {
                        composable(RepositoryScreen.routeName) {
                            RepoListScreen(navController = navController)
                        }
                        composable(
                            RepositoryDetailScreen.routeName + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )) {

                            val id = it.arguments?.getInt("id")
                            RepositoryDetailScreen(id = id, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
package com.example.squareincrepositories.features.github_repos.presentation

sealed class Screen(val routeName: String) {
    object RepositoryScreen : Screen("repository_screen")
    object RepositoryDetailScreen : Screen("repository_detail_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(routeName)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
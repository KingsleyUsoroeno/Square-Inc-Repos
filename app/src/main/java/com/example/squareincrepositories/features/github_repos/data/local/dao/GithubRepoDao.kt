package com.example.squareincrepositories.features.github_repos.data.local.dao

import androidx.room.*
import com.example.squareincrepositories.features.github_repos.data.local.entity.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepos(githubRepos: List<GithubRepoEntity>)

    @Delete
    suspend fun deleteGithubRepo(githubRepoEntity: GithubRepoEntity)

    @Query("SELECT * FROM GithubRepoEntity WHERE id = :id LIMIT 1")
    suspend fun findGithubRepoById(id: Int): GithubRepoEntity?

    @Query("SELECT * FROM GithubRepoEntity")
    suspend fun getGithubRepos(): List<GithubRepoEntity>

    @Query("SELECT * FROM GithubRepoEntity")
    fun observeGithubRepos(): Flow<List<GithubRepoEntity>>

    @Query("UPDATE GithubRepoEntity SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmarkStatus(id: Int, isBookmarked: Boolean)
}
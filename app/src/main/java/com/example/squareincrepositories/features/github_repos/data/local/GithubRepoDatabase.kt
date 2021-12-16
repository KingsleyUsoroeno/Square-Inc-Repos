package com.example.squareincrepositories.features.github_repos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.squareincrepositories.features.github_repos.data.local.dao.GithubRepoDao
import com.example.squareincrepositories.features.github_repos.data.local.entity.GithubRepoEntity

@Database(entities = [GithubRepoEntity::class], version = 1, exportSchema = false)
abstract class GithubRepoDatabase : RoomDatabase() {

    abstract val githubRepoDao: GithubRepoDao

    companion object {
        private const val DATABASE_NAME: String = "github_repos_db"
        fun build(context: Context): GithubRepoDatabase = Room.databaseBuilder(
            context.applicationContext,
            GithubRepoDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
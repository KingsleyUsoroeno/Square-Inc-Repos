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
        @Volatile
        private lateinit var INSTANCE: GithubRepoDatabase

        fun createGithubRepoDatabase(context: Context): GithubRepoDatabase {
            if (!this::INSTANCE.isInitialized) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GithubRepoDatabase::class.java, "github_repos_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
            return INSTANCE
        }
    }
}
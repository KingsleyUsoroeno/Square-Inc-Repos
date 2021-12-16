package com.example.squareincrepositories.features.github_repos.di

import android.content.Context
import com.example.squareincrepositories.BuildConfig
import com.example.squareincrepositories.features.github_repos.data.local.GithubRepoDatabase
import com.example.squareincrepositories.features.github_repos.data.remote.GithubApiService
import com.example.squareincrepositories.features.github_repos.data.remote.interceptor.NoInternetInterceptor
import com.example.squareincrepositories.features.github_repos.data.repository.GithubRepoRepositoryImpl
import com.example.squareincrepositories.features.github_repos.domain.repository.GithubRepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubRepoModule {

    @[Provides Singleton]
    fun provideGithubRepoRepository(
        db: GithubRepoDatabase,
        githubApiService: GithubApiService
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(githubApiService, db.githubRepoDao)
    }

    @[Provides Singleton]
    fun provideGithubRepoDatabase(
        @ApplicationContext context: Context
    ): GithubRepoDatabase {
        return GithubRepoDatabase.build(context)
    }

    @[Provides Singleton]
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    @[Provides Singleton]
    fun provideRetrofitBuilder(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            baseUrl("https://api.github.com/")
        }.build()
    }
}
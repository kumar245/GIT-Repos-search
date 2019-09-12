package dev.chaitan.gitusersearch.di.module

import dagger.Module
import dagger.Provides
import dev.chaitan.gitusersearch.BuildConfig
import dev.chaitan.gitusersearch.api.client.RetrofitClient
import dev.chaitan.gitusersearch.api.repository.GitRepository
import dev.chaitan.gitusersearch.api.service.GitService
import javax.inject.Singleton

@Module
class ApiServiceProvider {
    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Provides
    @Singleton
    fun provideGitService(retrofitClient: RetrofitClient): GitService {
        return retrofitClient
            .getRetrofit(BuildConfig.GIT_API_URL)
            .create(GitService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitRepository(gitService: GitService): GitRepository {
        return GitRepository(gitService)
    }
}
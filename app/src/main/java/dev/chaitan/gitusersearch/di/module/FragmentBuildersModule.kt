package dev.chaitan.gitusersearch.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.chaitan.gitusersearch.ui.detail.GitUserDetailFragment
import dev.chaitan.gitusersearch.ui.main.UserSearchFragment

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun provideUserSearchFragment(): UserSearchFragment

    @ContributesAndroidInjector
    abstract fun provideGitUserDetailFragment(): GitUserDetailFragment
}
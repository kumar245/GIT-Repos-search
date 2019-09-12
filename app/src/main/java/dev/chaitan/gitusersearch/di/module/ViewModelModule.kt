package dev.chaitan.gitusersearch.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.chaitan.gitusersearch.di.ViewModelFactory
import dev.chaitan.gitusersearch.di.ViewModelKey
import dev.chaitan.gitusersearch.ui.detail.GitUserDetailViewModel
import dev.chaitan.gitusersearch.ui.main.UserSearchViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserSearchViewModel::class)
    abstract fun bindUserSearchViewModel(userSearchViewModel: UserSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GitUserDetailViewModel::class)
    abstract fun bindGitUserDetailViewModel(gitUserDetailViewModel: GitUserDetailViewModel): ViewModel
}
package dev.chaitan.gitusersearch.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.chaitan.gitusersearch.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}
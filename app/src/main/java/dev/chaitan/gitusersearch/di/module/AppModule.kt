package dev.chaitan.gitusersearch.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dev.chaitan.gitusersearch.di.annotaton.ApplicationContext
import javax.inject.Singleton

@Module()
internal object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    @ApplicationContext
    fun provideContext(application: Application): Context {
        return application
    }


}
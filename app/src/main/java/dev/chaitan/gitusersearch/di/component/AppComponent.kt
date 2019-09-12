package dev.chaitan.gitusersearch.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.chaitan.gitusersearch.app.GitUserSearchApplication
import dev.chaitan.gitusersearch.di.module.*
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class,
        ActivityModule::class,
        ApiServiceProvider::class,
        ViewModelModule::class,
        FragmentBuildersModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GitUserSearchApplication)
}
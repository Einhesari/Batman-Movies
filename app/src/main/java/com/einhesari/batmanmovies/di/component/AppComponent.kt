package com.einhesari.batmanmovies.di.component

import android.content.Context
import com.einhesari.batmanmovies.di.module.*
import com.einhesari.batmanmovies.presentation.MoviesFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        MoviesRemoteDataSourceModule::class,
        MoviesRemoteDataSourceModule::class,
        MovieRepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val viewModelProviderFactory: ViewModelComponent.Factory
}

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(moviesFragment: MoviesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}

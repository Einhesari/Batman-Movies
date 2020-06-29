package com.einhesari.batmanmovies.di.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.einhesari.batmanmovies.ViewModelProviderFactory
import com.einhesari.batmanmovies.di.module.ApiModule
import com.einhesari.batmanmovies.di.module.MovieRepositoryModule
import com.einhesari.batmanmovies.di.module.MoviesRemoteDataSourceModule
import com.einhesari.batmanmovies.presenter.MoviesFragment
import com.einhesari.zomatosample.di.module.ViewModelFactoryModule
import com.einhesari.zomatosample.di.module.ViewModelModule
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

package com.einhesari.batmanmovies.di.component

import android.content.Context
import com.einhesari.batmanmovies.di.module.ApiModule
import com.einhesari.batmanmovies.di.module.MoviesRemoteDataSourceModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApiModule::class,
        MoviesRemoteDataSourceModule::class,
        MoviesRemoteDataSourceModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

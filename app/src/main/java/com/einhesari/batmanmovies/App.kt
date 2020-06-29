package com.einhesari.batmanmovies

import android.app.Application
import com.einhesari.batmanmovies.di.component.AppComponent
import com.einhesari.batmanmovies.di.component.DaggerAppComponent

lateinit var component: AppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerAppComponent
            .factory()
            .create(this)
    }

}
package com.honnalmanja.androidkotlinmvvm

import android.app.Application
import com.honnalmanja.androidkotlinmvvm.di.*

class TaskManager : Application() {

    companion object {
        private lateinit var app: TaskManager
        private lateinit var appComponent: AppComponent

        fun getApp():TaskManager {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .preferenceModule(PreferenceModule())
            .retrofitModule(RetrofitModule())
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}
package com.honnalmanja.androidkotlinmvvm

import android.app.Application
import com.honnalmanja.androidkotlinmvvm.di.Injector
import com.honnalmanja.androidkotlinmvvm.di.core.*
import com.honnalmanja.androidkotlinmvvm.di.task.TaskSubComponent
import com.honnalmanja.androidkotlinmvvm.di.user.UserSubComponent

class TaskManager : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .retrofitModule(RetrofitModule(BuildConfig.BASE_URL))
            .build()
    }

    override fun createUserSubComponent(): UserSubComponent {
        return appComponent.userSubComponent().create()
    }

    override fun createTaskSubComponent(): TaskSubComponent {
        return appComponent.taskSubComponent().create()
    }


}
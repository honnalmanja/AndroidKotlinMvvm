package com.honnalmanja.androidkotlinmvvm.di.core

import android.content.Context
import com.honnalmanja.androidkotlinmvvm.di.task.TaskSubComponent
import com.honnalmanja.androidkotlinmvvm.di.user.UserSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [UserSubComponent::class, TaskSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context{
        return context.applicationContext
    }

}
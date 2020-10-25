package com.honnalmanja.androidkotlinmvvm.di

import com.honnalmanja.androidkotlinmvvm.model.repository.TaskRepository
import com.honnalmanja.androidkotlinmvvm.model.repository.UserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class, PreferenceModule::class])
interface AppComponent {

    fun inject(taskRepository: TaskRepository?)

    fun inject(userRepository: UserRepository?)
}
package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.di.task.TaskSubComponent
import com.honnalmanja.androidkotlinmvvm.di.user.UserSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, PreferenceModule::class, DataBaseModule::class,
                        RemoteDataModule::class, LocalDataModule::class, CacheDataModule::class,
                        RepositoryModule::class, UseCaseModule::class]
)
interface AppComponent {

    fun userSubComponent(): UserSubComponent.Factory

    fun taskSubComponent(): TaskSubComponent.Factory
}
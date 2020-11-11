package com.honnalmanja.androidkotlinmvvm.di.core

import android.content.Context
import androidx.room.Room
import com.honnalmanja.androidkotlinmvvm.data.local.db.TaskDao
import com.honnalmanja.androidkotlinmvvm.data.local.db.TaskDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context, TaskDatabase::class.java,"TaskDB"
        ).build()
    }


    @Singleton
    @Provides
    fun provideTaskDAO(database: TaskDatabase): TaskDao {
        return database.taskDAO()
    }
}
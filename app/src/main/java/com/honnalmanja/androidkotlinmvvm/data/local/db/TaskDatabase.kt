package com.honnalmanja.androidkotlinmvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.honnalmanja.androidkotlinmvvm.data.model.db.Task

@Database(
    entities = [Task::class],
    version = 1, exportSchema = false
)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDAO(): TaskDao
}
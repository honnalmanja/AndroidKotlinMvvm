package com.honnalmanja.androidkotlinmvvm.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (

    @PrimaryKey
    @ColumnInfo(name = "task_id")
    var taskID: String,

    @ColumnInfo(name = "task_description")
    var taskDescription: String? = null,

    @ColumnInfo(name = "task_completed")
    var taskCompleted: Boolean = false,

    @ColumnInfo(name = "task_created_at")
    var taskCreatedAt: String,

    @ColumnInfo(name = "task_updated_at")
    var taskUpdatedAt: String,

)
package com.honnalmanja.androidkotlinmvvm.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.honnalmanja.androidkotlinmvvm.data.model.db.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllTasks(taskList: List<Task>)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>?

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM tasks WHERE task_id = :taskID")
    suspend fun getATask(taskID: Int)

    @Query("DELETE FROM tasks WHERE task_id = :taskID")
    suspend fun deleteATask(taskID: Int)

}
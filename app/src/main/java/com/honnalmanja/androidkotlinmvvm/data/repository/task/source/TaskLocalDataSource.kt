package com.honnalmanja.androidkotlinmvvm.data.repository.task.source

import com.honnalmanja.androidkotlinmvvm.data.model.db.Task


interface TaskLocalDataSource {

    suspend fun getAuthToken(): String?

    suspend fun getATask(id: String?): Task?

    suspend fun getAllTask(): List<Task>?

    suspend fun saveATask(task: Task)

    suspend fun saveAllTasks(taskList: List<Task>)

    suspend fun deleteATask(id: String?)

    suspend fun deleteAllTask()

    suspend fun updateATask(taskID: String, isCompleted: Boolean, description: String?, createdAt: String)

}


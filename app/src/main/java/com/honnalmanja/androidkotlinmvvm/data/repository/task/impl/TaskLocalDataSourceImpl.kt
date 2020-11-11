package com.honnalmanja.androidkotlinmvvm.data.repository.task.impl

import com.honnalmanja.androidkotlinmvvm.data.local.db.TaskDao
import com.honnalmanja.androidkotlinmvvm.data.local.prefs.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource
import okhttp3.internal.concurrent.Task

class TaskLocalDataSourceImpl(
    private val appPreference: AppPreference,
    private val taskDao: TaskDao
): TaskLocalDataSource {

    override suspend fun getAuthToken(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getATask(id: String?): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTask(): List<Tasks>? {
        TODO("Not yet implemented")
    }

    override suspend fun saveATask(task: Tasks) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAllTasks(taskList: List<Tasks>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteATask(id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTask() {
        TODO("Not yet implemented")
    }


}
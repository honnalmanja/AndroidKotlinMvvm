package com.honnalmanja.androidkotlinmvvm.data.repository.task.impl

import com.honnalmanja.androidkotlinmvvm.data.local.db.TaskDao
import com.honnalmanja.androidkotlinmvvm.data.local.prefs.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.model.db.Task
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource

class TaskLocalDataSourceImpl(
    private val appPreference: AppPreference,
    private val taskDao: TaskDao
): TaskLocalDataSource {

    override suspend fun getAuthToken(): String? {
        return appPreference.getUserToken()
    }

    override suspend fun getATask(id: String?): Task? {
        return taskDao.getATask(id)
    }

    override suspend fun getAllTask(): List<Task>? {
        return taskDao.getAllTasks()
    }

    override suspend fun saveATask(task: Task) {
        return taskDao.saveATasks(task)
    }

    override suspend fun saveAllTasks(taskList: List<Task>) {
        return taskDao.saveAllTasks(taskList)
    }

    override suspend fun deleteATask(id: String?) {
        return taskDao.deleteATask(id)
    }

    override suspend fun deleteAllTask() {
        return taskDao.deleteAllTasks()
    }

    override suspend fun updateATask(
        taskID: String,
        isCompleted: Boolean,
        description: String?,
        createdAt: String
    ) {
        taskDao.updateATask(taskID, isCompleted, description, createdAt)
    }


}
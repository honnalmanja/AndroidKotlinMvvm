package com.honnalmanja.androidkotlinmvvm.data.repository.task.impl

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskCacheDataSource

class TaskCacheDataSourceImpl: TaskCacheDataSource {

    private var taskList: ArrayList<Tasks> = ArrayList()

    override suspend fun getTaskList(): List<Tasks>? {
        return taskList
    }

    override suspend fun saveTaskList(taskList: List<Tasks>) {
        this.taskList.clear()
        this.taskList.addAll(taskList)
    }

}
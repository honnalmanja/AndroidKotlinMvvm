package com.honnalmanja.androidkotlinmvvm.data.repository.task.source

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks

interface TaskCacheDataSource {

    suspend fun getTaskList() : List<Tasks>?

    suspend fun saveTaskList(taskList: List<Tasks>)

}
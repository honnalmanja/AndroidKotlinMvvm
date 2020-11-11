package com.honnalmanja.androidkotlinmvvm.data.model.app

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks

data class TaskLiveData(
    var statusCode: Int?,
    var message: String?,
    var task: Tasks?,
    var taskList: List<Tasks>?
)
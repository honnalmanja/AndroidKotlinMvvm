package com.honnalmanja.androidkotlinmvvm.data.model.remote.task

import com.google.gson.annotations.SerializedName

data class TaskResponse (
    @SerializedName("tasks")
    val taskList: List<Tasks>
)
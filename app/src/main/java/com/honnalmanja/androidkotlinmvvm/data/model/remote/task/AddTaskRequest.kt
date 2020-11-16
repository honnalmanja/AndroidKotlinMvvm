package com.honnalmanja.androidkotlinmvvm.data.model.remote.task

import com.google.gson.annotations.SerializedName

data class AddTaskRequest(
    @SerializedName("description")
    var description: String?,

    @SerializedName("isCompleted")
    var isCompleted: Boolean = false
)
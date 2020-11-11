package com.honnalmanja.androidkotlinmvvm.data.model.remote.task

import com.google.gson.annotations.SerializedName

data class UpdateTaskRequest(
    @SerializedName("_id")
    var taskID: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("completed")
    var isCompleted: Boolean? = false
)
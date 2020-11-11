package com.honnalmanja.androidkotlinmvvm.data.model.remote.task

import com.google.gson.annotations.SerializedName
import java.util.*

data class Tasks(
    @SerializedName("_id")
    var taskID: String? = null,

    @SerializedName("description")
    var taskDescription: String? = null,

    @SerializedName("completed")
    var taskCompleted: Boolean = false,

    @SerializedName("createdAt")
    var createdAt:Date? = null,

    @SerializedName("updatedAt")
    var updatedAt:Date? = null
)
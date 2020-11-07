package com.honnalmanja.androidkotlinmvvm.data.model.remote

import com.google.gson.annotations.SerializedName
import java.util.*

class Tasks(@SerializedName("_id")
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
package com.honnalmanja.androidkotlinmvvm.data.model.remote.task

import com.google.gson.annotations.SerializedName
import java.util.*

class UpdateTaskRequest(description: String?, isCompleted: Boolean) {

    @SerializedName("description")
    var description: String? = description

    @SerializedName("completed")
    var isCompleted: Boolean = isCompleted
}

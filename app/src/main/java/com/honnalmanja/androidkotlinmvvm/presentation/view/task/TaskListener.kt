package com.honnalmanja.androidkotlinmvvm.presentation.view.task

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks

interface TaskListener {

    fun onTaskSelected(position: Int, tasks: Tasks)

}
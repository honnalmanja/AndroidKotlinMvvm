package com.honnalmanja.androidkotlinmvvm.di

import com.honnalmanja.androidkotlinmvvm.di.task.TaskSubComponent
import com.honnalmanja.androidkotlinmvvm.di.user.UserSubComponent

interface Injector {

    fun createUserSubComponent(): UserSubComponent

    fun createTaskSubComponent(): TaskSubComponent

}
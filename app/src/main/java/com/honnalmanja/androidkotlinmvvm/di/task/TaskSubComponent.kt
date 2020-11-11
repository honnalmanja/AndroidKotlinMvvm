package com.honnalmanja.androidkotlinmvvm.di.task

import com.honnalmanja.androidkotlinmvvm.presentation.view.task.AddTaskFragment
import com.honnalmanja.androidkotlinmvvm.presentation.view.task.TaskListFragment
import com.honnalmanja.androidkotlinmvvm.presentation.view.user.LoginFragment
import dagger.Subcomponent

@TaskScope
@Subcomponent(modules = [TaskModule::class])
interface TaskSubComponent {

    fun inject(taskListFragment: TaskListFragment)

    fun inject(addTaskFragment: AddTaskFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() : TaskSubComponent
    }

}
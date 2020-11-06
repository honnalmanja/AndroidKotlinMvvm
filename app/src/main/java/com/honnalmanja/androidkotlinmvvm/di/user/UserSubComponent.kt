package com.honnalmanja.androidkotlinmvvm.di.user

import com.honnalmanja.androidkotlinmvvm.presentation.view.task.TaskListFragment
import com.honnalmanja.androidkotlinmvvm.presentation.view.user.LoginFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubComponent {

    fun inject(loginFragment: LoginFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() : UserSubComponent
    }

}
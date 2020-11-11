package com.honnalmanja.androidkotlinmvvm.di.user

import com.honnalmanja.androidkotlinmvvm.presentation.view.task.TaskListFragment
import com.honnalmanja.androidkotlinmvvm.presentation.view.user.LoginFragment
import com.honnalmanja.androidkotlinmvvm.presentation.view.user.SignUpFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubComponent {

    fun inject(loginFragment: LoginFragment)

    fun inject(signUpFragment: SignUpFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() : UserSubComponent
    }

}
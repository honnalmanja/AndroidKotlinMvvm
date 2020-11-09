package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.honnalmanja.androidkotlinmvvm.data.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.User
import com.honnalmanja.androidkotlinmvvm.domain.FakeUserRepository
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    var emailError = MutableLiveData<Int>()

    @Before
    fun setUp() {
        val fakeUserRepository = FakeUserRepository()
        val userUseCase = UserUseCase(fakeUserRepository)
        viewModel = LoginViewModel(userUseCase)
    }

    @Test
    fun loginUserTest() {
        val email = "manju@example.com"
        val user = User("1","Manju", email, 10, "https://siteaname.domain/images/manju.jpg")
        val successData = UserLiveData(200, "Success", user, null, "FakeToken")
        val failureData = UserLiveData(404, "Email or Password wrong", null, null, null)
        val networkErrorData = UserLiveData(500, "Network Error, Try again", null, null,null)

        val userLiveData = viewModel.loginUser(email, "asdasdas").getOrAwaitValue()

        Truth.assertThat(successData.statusCode).isEqualTo(userLiveData.statusCode)
        Truth.assertThat(successData.user?.userEmail).isEqualTo(userLiveData.user?.userEmail)


    }
}
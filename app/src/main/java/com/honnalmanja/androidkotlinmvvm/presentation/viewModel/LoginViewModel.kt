package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.data.model.app.LoginUser
import com.honnalmanja.androidkotlinmvvm.data.model.remote.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class LoginViewModel(
    private val userUseCase: UserUseCase
) : ViewModel(){

    private val _TAG = "LoginViewModel"

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var emailError = MutableLiveData<Int>()
    var passwordError = MutableLiveData<Int>()

    var canLogin = MutableLiveData<Boolean>()

    fun loginUser(email: String?, password: String?) = liveData {

        val loginUserRequest = LoginUserRequest(email, password)
        val response = userUseCase.loginUser(loginUserRequest)
        emit(response)
    }

    fun validateFields(emailLD: MutableLiveData<String>, passwordLD: MutableLiveData<String>){

        //LogUtils.logD(_TAG, "postLogin: Email: ${emailLD.value}")
        //LogUtils.logD(_TAG, "postLogin: Password: ${passwordLD.value}")

        val email: String? = emailLD.value
        val password: String? = passwordLD.value

        canLogin.value = false

        when {
            email == null -> emailError.value = CommonUtil.nullBlankErrorCode

            email.trim().isBlank() -> emailError.value = CommonUtil.nullBlankErrorCode

            !CommonUtil.isValidEmail(email) -> emailError.value = 2

            password == null -> passwordError.value = CommonUtil.nullBlankErrorCode

            password.trim().isBlank() -> passwordError.value = CommonUtil.nullBlankErrorCode

            else -> {
                emailError.value = CommonUtil.successCode
                passwordError.value = CommonUtil.successCode
                canLogin.value = true
            }
        }

    }

}
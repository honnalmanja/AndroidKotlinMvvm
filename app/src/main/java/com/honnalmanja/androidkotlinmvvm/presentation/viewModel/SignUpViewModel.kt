package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class SignUpViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _TAG = "SignUpViewModel"

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()

    var emailError = MutableLiveData<Int>()
    var nameError = MutableLiveData<Int>()
    var passwordError = MutableLiveData<Int>()
    var cPasswordError = MutableLiveData<Int>()

    var canCreateUser = MutableLiveData<Boolean>()

    fun validateFields(emailLD: MutableLiveData<String>,
                       nameLD: MutableLiveData<String>,
                       passwordLD: MutableLiveData<String>,
                       cPasswordLD: MutableLiveData<String>,
    ){

        LogUtils.logD(_TAG, "signUpFields Email: ${emailLD.value}")
        LogUtils.logD(_TAG, "signUpFields Password: ${passwordLD.value}")
        LogUtils.logD(_TAG, "signUpFields Name: ${nameLD.value}")
        LogUtils.logD(_TAG, "signUpFields cPassword: ${cPasswordLD.value}")

        val email: String? = emailLD.value
        val name: String? = nameLD.value
        val password: String? = passwordLD.value
        val confirmPassword: String? = cPasswordLD.value

        canCreateUser.value = false

        when {
            email == null -> emailError.value = CommonUtil.nullBlankErrorCode

            email.trim().isBlank() -> emailError.value = CommonUtil.nullBlankErrorCode

            !CommonUtil.isValidEmail(email) -> emailError.value = 2

            name == null -> nameError.value = CommonUtil.nullBlankErrorCode

            name.trim().isBlank() -> nameError.value = CommonUtil.nullBlankErrorCode

            password == null -> passwordError.value = CommonUtil.nullBlankErrorCode

            password.trim().isBlank() -> passwordError.value = CommonUtil.nullBlankErrorCode

            confirmPassword == null -> cPasswordError.value = CommonUtil.nullBlankErrorCode

            confirmPassword.trim().isBlank() -> cPasswordError.value = CommonUtil.nullBlankErrorCode

            !confirmPassword.trim().equals(password, true) -> cPasswordError.value = 2

            else -> {
                emailError.value = CommonUtil.successCode
                nameError.value = CommonUtil.successCode
                passwordError.value = CommonUtil.successCode
                cPasswordError.value = CommonUtil.successCode
                canCreateUser.value = true
            }
        }

    }

    fun postUser(email: String?, name: String?, password: String?) = liveData{

        val createUserRequest = CreateUserRequest(email, password, name, 0)
        val response = userUseCase.createUser(createUserRequest)
        emit(response)

    }

}
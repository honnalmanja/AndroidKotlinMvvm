package com.honnalmanja.androidkotlinmvvm.data.model.app


import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil


class LoginUser(var email: String?, var password: String?) {

    fun isEmailBlank(): Boolean? = if(email == null) {
        false
    } else {
        email?.isBlank()
    }

    fun isEmailValid(): Boolean? = if(email == null) {
        false
    } else {
        CommonUtil.isValidEmail(email!!)
    }

    fun isPasswordBlank(): Boolean? = if(password == null) {
        false
    } else {
        password?.isBlank()
    }


}
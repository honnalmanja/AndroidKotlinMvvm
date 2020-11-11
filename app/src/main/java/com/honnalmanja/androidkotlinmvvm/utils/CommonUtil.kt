package com.honnalmanja.androidkotlinmvvm.utils

import com.google.android.material.textfield.TextInputLayout

class CommonUtil {

    companion object{

        private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        const val nullBlankErrorCode = 1

        const val successCode = 0

        fun isValidEmail(email: String): Boolean{
            return email.matches(emailPattern.toRegex())
        }

        fun showInputError(message: String, textInputLayout: TextInputLayout){
            textInputLayout.error = message
            textInputLayout.isErrorEnabled = true
        }

        fun disableTextError(textInputLayout: TextInputLayout){
            textInputLayout.error = null
            textInputLayout.isErrorEnabled = false
        }

    }

}
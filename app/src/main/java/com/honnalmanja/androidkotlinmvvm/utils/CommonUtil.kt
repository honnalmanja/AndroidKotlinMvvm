package com.honnalmanja.androidkotlinmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.*

class CommonUtil {

    companion object{

        private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        private const val dateTimePattern = "yyyy-MM-dd HH:mm:ss.SSS"

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

        fun convertStringToDate(date: String): Date{
            val format = SimpleDateFormat(dateTimePattern, Locale.ENGLISH)
            return format.parse(date)
        }

        fun convertDateToString(date: Date) : String {
            val format = SimpleDateFormat(dateTimePattern, Locale.ENGLISH)
            return format.format(date)
        }

        fun getCurrentDateTime(): Date {
            val currentDate = SimpleDateFormat(dateTimePattern, Locale.ENGLISH).format(Date())
            return convertStringToDate(currentDate)
        }

    }

}
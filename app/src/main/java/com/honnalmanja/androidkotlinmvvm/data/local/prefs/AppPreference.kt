package com.honnalmanja.androidkotlinmvvm.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import io.reactivex.Single
import java.lang.Exception

class AppPreference(context: Context) {


    private val TAG:String = "AppPreference"
    private var sharedPreference: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )

    private val appContext: Context = context

    /**
     * Function to save user Token and user detail in sharedPreference
     * @param user : User object sent from server
     * @param token: User Token string got from server
     */
     suspend fun saveUserAndToken(user: User?, token: String?): Boolean  {
        try {
            LogUtils.logD(TAG,"user $user")
            LogUtils.logD(TAG,"token $token")
            val editor:SharedPreferences.Editor = sharedPreference.edit()
            editor.putString(appContext.getString(R.string.key_user_token), token)
            if(user != null){
                val gSon = Gson()
                val userJSON = gSon.toJson(user)
                editor.putString(appContext.getString(R.string.key_logged_in_user),userJSON)
            } else {
                editor.putString(appContext.getString(R.string.key_logged_in_user),null)
            }
            editor.apply()
        } catch (e: Exception){
            return false
        }
        return true
    }

    /**
     * Function to get the saved token
     */
    fun getUserToken(): String? {
        return sharedPreference.getString(appContext.getString(R.string.key_user_token),null)
    }

    /**
     * Function to get the saved User
     */
    fun getUserDetails(): Single<User> {
        val userString: String? = sharedPreference.getString(appContext.getString(R.string.key_logged_in_user), "")
        userString?.let { LogUtils.logD(TAG, it) }
        val gSon = Gson()
        val user: User = gSon.fromJson(userString,null)
        return Single.just(user)
    }

}
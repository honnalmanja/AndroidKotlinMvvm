package com.honnalmanja.androidkotlinmvvm.utils

import android.util.Log
import com.honnalmanja.androidkotlinmvvm.BuildConfig

class LogUtils {

    companion object{

        fun logD(TAG: String, message:String) {
            if (BuildConfig.DEBUG){
                Log.d(TAG, message)
            }
        }

        fun logE(TAG: String, message:String) {
            if (BuildConfig.DEBUG){
                Log.e(TAG, message)
            }
        }

        fun logI(TAG: String, message:String) {
            if (BuildConfig.DEBUG){
                Log.i(TAG, message)
            }
        }

    }

}
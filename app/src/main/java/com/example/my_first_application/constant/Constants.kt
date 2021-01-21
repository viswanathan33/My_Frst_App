package com.example.my_first_application.constant

import android.content.Context
import android.content.Intent
class Constants {
    companion object BundleKey{
        fun<T> callActivity(context:Context,classType: Class<T>)
        {
            val intent = Intent(context, classType)
            context.startActivity(intent)
        }
        const val share_pref = "shared pref"
        const val USER_INFO = "USER_INFO"
        const val mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val passwordPatternDigit = "^(?=.*[0-9])" + ".{0,}$"
        const val passwordPatternLowerCase = "^(?=.*[a-z])" + ".{0,}$"
        const val passwordPatternUpperCase = "^(?=.*[A-Z])" + ".{0,}$"
        const val passwordPatternSplChar = "^(?=.*[@#$%^&+=])" + ".{0,}$"

    }
}
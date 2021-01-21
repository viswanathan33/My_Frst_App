package com.example.my_first_application.constant

class Constants {
    companion object BundleKey{
        const val share_pref = "shared pref"
        const val USER_INFO = "USER_INFO"
        const val mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val passwordPattern = "^(?=.*[0-9])" + ".{0,}$"
        const val passwordPattern1 = "^(?=.*[a-z])" + ".{0,}$"
        const val passwordPattern2 = "^(?=.*[A-Z])" + ".{0,}$"
        const val passwordPattern3 = "^(?=.*[@#$%^&+=])" + ".{0,}$"

    }
}
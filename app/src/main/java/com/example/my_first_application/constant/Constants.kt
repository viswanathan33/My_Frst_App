package com.example.my_first_application.constant

class Constants {
    companion object BundleKey{
        const val name_condition = "enter name"
        const val mail_condition1 = "enter mail id"
        const val mail_condition2 = "invalid mail id"
        const val password_condition1 = "enter password"
        const val password_condition2 = "password is too short"
        const val password_condition3 = "A special character must occur atleast only once"
        const val password_condition4 = "A Uppercase case letter must occur atleast only once"
        const val password_condition5 = "A lower case letter must occur atleast only once"
        const val password_condition6 = "A digit must occur atleast only once"
        const val confirm_password_condition = "password does not match"
        const val share_pref = "shared pref"
        const val USER_INFO = "USER_INFO"
        const val mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val passwordPattern = "^(?=.*[0-9])" + ".{0,}$"
        const val passwordPattern1 = "^(?=.*[a-z])" + ".{0,}$"
        const val passwordPattern2 = "^(?=.*[A-Z])" + ".{0,}$"
        const val passwordPattern3 = "^(?=.*[@#$%^&+=])" + ".{0,}$"

    }
}
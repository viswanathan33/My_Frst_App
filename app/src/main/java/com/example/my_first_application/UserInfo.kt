package com.example.my_first_application

public class UserInfo {
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private var confirm_password: String? = null
    fun setName(name: String?) {
        this.name = name
    }
    fun setEmail(email: String?) {
        this.email = email
    }
    fun setpassword(password: String?) {
        this.password = password
    }
    fun setConfirm_password(confirm_password: String?) {
        this.confirm_password = confirm_password
    }
    fun getName():String?{
        return name
    }
    fun getEmail(): String? {
        return email
    }
    fun getPassword():String?{
        return password
    }
    fun getConfirm_password():String?{
        return confirm_password
    }
}
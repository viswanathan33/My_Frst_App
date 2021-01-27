package com.example.my_first_application.data

import androidx.lifecycle.LiveData
import com.example.my_first_application.data.UserDao
import com.example.my_first_application.data.UserDetails

class UserRepository(private val userdao: UserDao) {
    val readAllData:LiveData<List<UserDetails>> = userdao.readAllData()

     fun addUser(userDetails: UserDetails){
        userdao.addUser(userDetails)
    }
     fun addLogin(userLogIn: UserLogIn){
        userdao.addLogin(userLogIn)
    }
    fun logInCheck(mMail:String,mPassword:String):UserLogIn{
        val userLogIn:UserLogIn=userdao.logInCheck(mMail,mPassword)
        return userLogIn
    }
}
package com.example.my_first_application.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.my_first_application.data.UserDataBase
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
     val readAllData:LiveData<List<UserDetails>>
    private val repository: UserRepository
    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository= UserRepository(userDao)
        readAllData=repository.readAllData
    }
    fun addUser(userDetails: UserDetails){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(userDetails)
        }
    }
}
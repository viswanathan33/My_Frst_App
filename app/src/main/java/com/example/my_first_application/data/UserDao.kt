package com.example.my_first_application.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun addUser(userDetails: UserDetails)
    @Query("SELECT*FROM userTable")
    fun readAllData():LiveData<List<UserDetails>>
}
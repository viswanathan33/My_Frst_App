package com.example.my_first_application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "userTable")
class UserDetails(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "gender") val gender:String,
    @ColumnInfo(name = "mail") val mail:String,
    @ColumnInfo(name = "age") val age:String
) {

}
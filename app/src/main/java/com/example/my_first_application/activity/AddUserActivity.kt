package com.example.my_first_application.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.model.RegisterInfo
import com.google.gson.Gson

class AddUserActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register=findViewById<Button>(R.id.button_register)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        val actionBar = supportActionBar
        actionBar!!.hide()
        register.setOnClickListener {
           inserDataToDatabase()
            Constants.callActivity(this, HomeActivity::class.java)
            //Log.d("TAG","data===>"+json)
            finish()
        }
    }
    private fun inserDataToDatabase(){
        val name=findViewById<EditText>(R.id.personName)
        val mail=findViewById<EditText>(R.id.mail)
        val gender=findViewById<EditText>(R.id.gender)
        val age=findViewById<EditText>(R.id.age)
        val username=name.text.toString()
        val usergender=gender.text.toString()
        val usermail=mail.text.toString()
        val userage=age.text.toString()
        val userDetails=UserDetails(0,username,usergender,usermail,userage)
        mUserViewModel.addUser(userDetails)
        Toast.makeText(applicationContext, "added succesfully", Toast.LENGTH_SHORT).show()
    }
}
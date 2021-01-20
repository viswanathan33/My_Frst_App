package com.example.my_first_application

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val name=findViewById<EditText>(R.id.personName)
        val mail=findViewById<EditText>(R.id.mail)
        val gender=findViewById<EditText>(R.id.gender)
        val age=findViewById<EditText>(R.id.age)
        val register=findViewById<Button>(R.id.button_register)
        register.setOnClickListener {
            val gson=Gson()
            val registerInfo=RegisterInfo(name.text.toString(),gender.text.toString(),mail.text.toString(),age.text.toString())
            val json:String=gson.toJson(registerInfo)
            val intent=Intent()
            intent.putExtra(Constants.USER_INFO,json)
            setResult(Activity.RESULT_OK,intent)
            //Log.d("TAG","data===>"+json)
            finish()
        }
    }
}
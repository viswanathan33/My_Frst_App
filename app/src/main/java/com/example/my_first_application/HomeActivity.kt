package com.example.my_first_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val gson= Gson()
        val sharedPreferences=getSharedPreferences(Constants.BundleKey.share_pref, Context.MODE_PRIVATE)
        val json: String? =sharedPreferences.getString(Constants.BundleKey.USER_INFO,"")
        val userInfo = gson.fromJson(json, UserInfo::class.java)
        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.drop_menu, menu)
            return true
        }
        fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            return when (id) {
                R.id.item1 -> {
                    Toast.makeText(applicationContext, "item 1 selected", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.item2 -> {
                    Toast.makeText(applicationContext, "item 2 selected", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.item3 -> {
                    val editor: Editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                    editor.commit()
                    val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }
}
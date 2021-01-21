package com.example.my_first_application.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_application.R
import com.example.my_first_application.adapter.UserDetailsAdapter
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.pojo.RegisterInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val gson= Gson()
    private val userList = ArrayList<RegisterInfo>()
    private lateinit var userListAdapter: UserDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        userListAdapter = UserDetailsAdapter(userList)
        val layoutManager = LinearLayoutManager(applicationContext)
        val userListView: RecyclerView = findViewById(R.id.recycler_view)
        val floatButtonAdd=findViewById<FloatingActionButton>(R.id.floatingActionButton_add)
        userListView.layoutManager = layoutManager
        userListView.itemAnimator = DefaultItemAnimator()
        userListView.adapter = userListAdapter
        floatButtonAdd.setOnClickListener {
            val intent=Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==1){
                if (data!=null){
                    val json:String?=data.getStringExtra(Constants.USER_INFO)
                    val registerInfo= gson.fromJson(json, RegisterInfo::class.java)
                    userList.add(registerInfo)
                    userListAdapter.notifyItemChanged(0)

                }
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drop_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(applicationContext, "item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item2 -> {
                Toast.makeText(applicationContext, "item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logoutItem -> {
                val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
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
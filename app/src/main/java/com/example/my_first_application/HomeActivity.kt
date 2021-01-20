package com.example.my_first_application

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val gson= Gson()
    private val exampleList = ArrayList<RegisterInfo>()
    private lateinit var exampleAdapter: ExampleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        exampleAdapter = ExampleAdapter(exampleList)
        val layoutManager = LinearLayoutManager(applicationContext)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = exampleAdapter

        val add=findViewById<FloatingActionButton>(R.id.floatingActionButton_add)
        add.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==1){
                if (data!=null){
                    val json:String?=data.getStringExtra(Constants.USER_INFO)
                    val registerInfo= gson.fromJson(json,RegisterInfo::class.java)
                    exampleList.add(registerInfo)
                    exampleAdapter.notifyItemChanged(0)

                }
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drop_menu   , menu)
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
            R.id.item3 -> {
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
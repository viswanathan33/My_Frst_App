package com.example.my_first_application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_application.R
import com.example.my_first_application.model.RegisterInfo

internal class UserDetailsAdapter(private var userList:List<RegisterInfo>) : RecyclerView.Adapter<UserDetailsAdapter.ExampleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
       return ExampleViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.name.text=currentItem.getName()
        holder.gender.text=currentItem.getGender()
        holder.mail.text=currentItem.getEmail()
        holder.age.text=currentItem.getAge()

    }

    override fun getItemCount():Int{
        return userList.size
    }

   internal inner class ExampleViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name)
        val gender:TextView=itemView.findViewById(R.id.gender)
        val mail:TextView=itemView.findViewById(R.id.email)
        val age:TextView=itemView.findViewById(R.id.age)
    }
}
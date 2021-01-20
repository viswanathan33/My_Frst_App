package com.example.my_first_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ExampleAdapter(private var exampleList:List<RegisterInfo>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.example_item,parent,false)
       return ExampleViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem=exampleList[position]
        holder.name.text=currentItem.getName()
        holder.gender.text=currentItem.getGender()
        holder.mail.text=currentItem.getEmail()
        holder.age.text=currentItem.getAge()

    }

    override fun getItemCount():Int{
        return exampleList.size
    }

   internal inner class ExampleViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name)
        val gender:TextView=itemView.findViewById(R.id.gender)
        val mail:TextView=itemView.findViewById(R.id.email)
        val age:TextView=itemView.findViewById(R.id.age)
    }
}
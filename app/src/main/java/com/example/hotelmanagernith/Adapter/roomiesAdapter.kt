package com.example.hotelmanagernith.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.R

class roomiesAdapter(private val Roomies: ArrayList<String>):RecyclerView.Adapter<roomiesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roomiesAdapter   .MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.roomies_list,parent,false)
        return roomiesAdapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Roomies.size
    }

    override fun onBindViewHolder(holder: roomiesAdapter.MyViewHolder, position: Int) {
        val currentItem=Roomies[position]
        holder.rollNo.text= currentItem
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val rollNo:TextView=itemView.findViewById(R.id.tvRoomiesNames)
    }


}
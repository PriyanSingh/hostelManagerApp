package com.example.hotelmanagernith.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.Models.RoomData
import com.example.hotelmanagernith.R

class RoomAdapter: RecyclerView.Adapter<RoomAdapter.MyViewHolder>() {

    val userList=ArrayList<RoomData>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val roomname:TextView=itemView.findViewById(R.id.roomNo)
        val capacity:TextView=itemView.findViewById(R.id.capacity)
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(
            R.layout.room_list,parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem=userList[position]

        holder.roomname.text=currentitem.roomNo
        holder.capacity.text=currentitem.capacity
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList: List<RoomData>){
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}
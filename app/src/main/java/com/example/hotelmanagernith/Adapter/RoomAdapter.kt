package com.example.hotelmanagernith.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.Models.RoomData
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.activity.RoomDetails
import com.google.firebase.database.FirebaseDatabase

class RoomAdapter(val context: Context): RecyclerView.Adapter<RoomAdapter.MyViewHolder>() {

    val userList=ArrayList<RoomData>()
    var databaseRef =
    FirebaseDatabase.getInstance().getReference("rooms")

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
        holder.itemView.setOnClickListener{

            databaseRef=databaseRef.child((currentitem.roomNo).toString())
            databaseRef.get().addOnSuccessListener { dataSnapshot ->
                val count = dataSnapshot.child("count").getValue(Int::class.java)
                    ?: 0
                val capacity = dataSnapshot.child("capacity").getValue(String::class.java)
                    ?.toInt()
                    ?: 0
                if(count<capacity) {
                    val intent = Intent(context, RoomDetails::class.java)
                    intent.putExtra("Roomno", currentitem.roomNo)
                    context.startActivity(intent)
                }
                else{
                    Toast.makeText(context, "Room already booked", Toast.LENGTH_SHORT).show()
                }
                // Use the values here
            }.addOnFailureListener { exception ->
                // Handle any errors here
                Toast.makeText(context, "Error while fetching data", Toast.LENGTH_SHORT).show()
            }




        }
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
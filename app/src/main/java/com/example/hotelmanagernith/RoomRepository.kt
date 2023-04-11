package com.example.hotelmanagernith

import androidx.lifecycle.MutableLiveData
import com.example.hotelmanagernith.Models.RoomData
import com.google.firebase.database.*

class RoomRepository {

    private val databaseReference: DatabaseReference=FirebaseDatabase.getInstance().getReference("rooms")

    @Volatile private var INSTANSE: RoomRepository?=null

    fun getInstance(): RoomRepository{
        return INSTANSE?: synchronized(this){
            val instance=RoomRepository()
            INSTANSE=instance
            instance
        }
    }

    fun loadUsers(userList:MutableLiveData<List<RoomData>>){
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val roomlist: List<RoomData> =snapshot.children.map{dataSnapshot ->
                        dataSnapshot.getValue(RoomData::class.java)!!
                    }
                    userList.postValue(roomlist)
                }catch (_:java.lang.Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}
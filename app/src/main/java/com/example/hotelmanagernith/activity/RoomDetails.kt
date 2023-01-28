package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.ActivityRoomDetailsBinding

class RoomDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRoomDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)
    }
}
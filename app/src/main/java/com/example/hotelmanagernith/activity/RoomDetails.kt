package com.example .hotelmanagernith.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.ActivityRoomDetailsBinding
import kotlinx.android.synthetic.main.activity_room_details.*

class RoomDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRoomDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRoomDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomNo=intent.getStringExtra("Roomno").toString()
        Toast.makeText(this, roomNo, Toast.LENGTH_SHORT).show()
        supportActionBar?.title  =roomNo
    }
}
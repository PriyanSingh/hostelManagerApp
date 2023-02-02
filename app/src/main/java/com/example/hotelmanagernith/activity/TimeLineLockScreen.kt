package com.example.hotelmanagernith.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.hotelmanagernith.HostelNameSelector
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.fragments.ProfileFragment
import com.example.hotelmanagernith.fragments.Room_search_fragment
import kotlinx.android.synthetic.main.activity_sign_in.*



class TimeLineLockScreen : AppCompatActivity() {
    lateinit var TextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line_lock_screen)
        TextView=findViewById(R.id.Timer)
        val Room_search_fragment= Room_search_fragment()
        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                TextView.setText("Your time will Come after "+ millisUntilFinished/1000)



            }

            override fun onFinish() {




            }
        }
        timer.start()

    }
}
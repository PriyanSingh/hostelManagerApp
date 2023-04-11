package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hotelmanagernith.*
import com.example.hotelmanagernith.databinding.ActivityHomeBinding
import com.example.hotelmanagernith.fragments.ComplainFragment
import com.example.hotelmanagernith.fragments.ProfileFragment
import com.example.hotelmanagernith.fragments.RoomSearch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val k=intent.getStringExtra("message").toString()
        Toast.makeText(this, k, Toast.LENGTH_SHORT).show()

        val profileFragement= ProfileFragment()
        val complainFragment= ComplainFragment()
        val allotmentFragment= RoomSearch()

        makeCurrentFragment(profileFragement)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->makeCurrentFragment(profileFragement)
                R.id.allotment ->makeCurrentFragment(allotmentFragment)
            }
            true
        }


    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
    }


}
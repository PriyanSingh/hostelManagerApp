package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hotelmanagernith.*
import com.example.hotelmanagernith.databinding.ActivityHomeBinding
import com.example.hotelmanagernith.fragments.OutpassFragment
import com.example.hotelmanagernith.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val k=intent.getStringExtra("message").toString()
        Toast.makeText(this, k, Toast.LENGTH_SHORT).show()

        val profileFragement= ProfileFragment()
        val outpassFragment= OutpassFragment()
        val allotmentFragment= HostelNameSelector()

        makeCurrentFragment(profileFragement)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->makeCurrentFragment(profileFragement)
                R.id.outpass ->makeCurrentFragment(outpassFragment)
            }
            true
        }
        binding.fab.setOnClickListener{
            makeCurrentFragment(allotmentFragment)
        }

    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
    }


}
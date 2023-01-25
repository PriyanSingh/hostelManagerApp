package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hotelmanagernith.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val profileFragement= ProfileFragment()
        val outpassFragment= OutpassFragment()
        val allotmentFragment= HostelNameSelector()

        makeCurrentFragment(profileFragement)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->makeCurrentFragment(profileFragement)
                R.id.outpass ->makeCurrentFragment(outpassFragment)
            }
            true
        }
        fab.setOnClickListener{
            makeCurrentFragment(allotmentFragment)
        }

    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
    }


}
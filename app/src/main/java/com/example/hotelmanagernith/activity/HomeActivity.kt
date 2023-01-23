package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hotelmanagernith.HomeFragement
import com.example.hotelmanagernith.ProfileFragement
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.SettingsFragement
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragement= HomeFragement()
        val settingsFragement= SettingsFragement()
        val profileFragement= ProfileFragement()

        makeCurrentFragment(homeFragement)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->makeCurrentFragment( homeFragement)
                R.id.settings ->makeCurrentFragment(settingsFragement)
                R.id.fab -> makeCurrentFragment(profileFragement)

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
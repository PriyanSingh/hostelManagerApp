package com.example.hotelmanagernith

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragement=HomeFragement()
        val settingsFragement=SettingsFragement()
        val profileFragement=ProfileFragement()

        makeCurrentFragment(homeFragement)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->makeCurrentFragment( homeFragement)
                R.id.settings->makeCurrentFragment(settingsFragement)
                R.id.profile -> makeCurrentFragment(profileFragement)

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
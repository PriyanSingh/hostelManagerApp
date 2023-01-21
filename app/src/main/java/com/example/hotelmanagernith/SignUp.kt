package com.example.hotelmanagernith

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)




        val intentHom=Intent(this,HomeActivity::class.java)

        val buttonSig=findViewById<Button>(R.id.button)

        buttonSig.setOnClickListener(){
            startActivity(intentHom)
        }



    }
}
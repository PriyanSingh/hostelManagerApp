package com.example.hotelmanagernith

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intentReg= Intent(this,SignUp::class.java)
        val intentHom= Intent(this,HomeActivity::class.java)

        val buttonSig=findViewById<Button>(R.id.button)
        val buttonReg=findViewById<Button>(R.id.button2)

        buttonReg.setOnClickListener(){
            startActivity(intentReg)
        }
        buttonSig.setOnClickListener(){
            startActivity(intentHom)
        }

    }
}
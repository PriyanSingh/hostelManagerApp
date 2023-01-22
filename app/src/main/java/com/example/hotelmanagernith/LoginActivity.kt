package com.example.hotelmanagernith

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.hotelmanagernith.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        binding.button2.setOnClickListener{
            val intent =Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        binding.btnForgotPassword.setOnClickListener{
            val intent=Intent(this,ResetPassword::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email=binding.editTextTextPersonName.text.toString()
            val pass = binding.editTextTextPersonName3.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val verification=firebaseAuth.currentUser?.isEmailVerified
                            if(verification==true) {
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(this,"Invalid username or password", Toast.LENGTH_SHORT).show()
                        }

                    }

            }else{
                    Toast.makeText(this, "Incorrect username and password", Toast.LENGTH_SHORT).show()
            }

        }


    }
}
package com.example.hotelmanagernith

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagernith.databinding.ActivityResetPasswordBinding
import com.example.hotelmanagernith.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {


    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth=FirebaseAuth.getInstance()
        binding= ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnResetPassword.setOnClickListener{
            val email=binding.editEmail.text.toString()
            if(email.isNotEmpty()){
                if(email.endsWith("@nith.ac.in")){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            finish()
                            Toast.makeText(
                                this,
                                "password reset link sent successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "User not registered or connection error",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }else{
                    Toast.makeText(this, "It seems that your are not registered. Please sign up to register", Toast.LENGTH_SHORT).show()
                }


            }else{
                Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
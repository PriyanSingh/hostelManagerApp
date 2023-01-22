package com.example.hotelmanagernith

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.hotelmanagernith.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth



class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding =ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.button.setOnClickListener{
            val email=binding.editTextTextPersonName2.text.toString()
            val pass = binding.editTextTextPersonName5.text.toString()
            val confirmPass=binding.editTextTextPersonName.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass==confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){

                            firebaseAuth.currentUser?.sendEmailVerification()
                                ?.addOnSuccessListener {
                                    Toast.makeText(this,
                                        "Click on the link sent in email to verify your account",
                                        Toast.LENGTH_LONG).show()
                                }
                                ?.addOnFailureListener{
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                }
                            val intent=Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }
                }else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty fields are not allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
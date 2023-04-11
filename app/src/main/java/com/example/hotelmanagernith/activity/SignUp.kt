package com.example.hotelmanagernith.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.ActivitySignUpBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var builder:AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding =ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        builder=AlertDialog.Builder(this)

        binding.button.setOnClickListener{
            val email=binding.editTextTextPersonName2.text.toString()
            val pass = binding.editTextTextPersonName5.text.toString()
            val confirmPass=binding.editTextTextPersonName.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(email.endsWith("@nith.ac.in")) {
                    if (pass == confirmPass) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {

                                    firebaseAuth.currentUser?.sendEmailVerification()
                                        ?.addOnSuccessListener {
                                        builder.setTitle("Email sent")
                                            .setMessage("Email verification link sent successfully")
                                            .setCancelable(false)
                                            .setPositiveButton("OK"){dialogInterface,it->
                                                finish()
                                            }
                                            .show()

                                        }
                                        ?.addOnFailureListener {
                                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this,
                                        it.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                    } else {
                        Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Please use your collage ID", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty fields are not allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
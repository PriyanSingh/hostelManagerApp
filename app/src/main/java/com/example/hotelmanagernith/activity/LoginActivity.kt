package com.example.hotelmanagernith.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagernith.fragments.ProfileFragment
import com.example.hotelmanagernith.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        binding.button2.setOnClickListener{
            val intent =Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.btnForgotPassword.setOnClickListener{
            val intent=Intent(this,ResetPassword::class.java)
//            sortData()
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
                                val fragment= ProfileFragment()
                                val mess=email.subSequence(0,8).toString()
                                val intent = Intent(this, HomeActivity::class.java)
                                intent.putExtra("message",mess)
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

    private fun sortData() {
        val db=FirebaseDatabase.getInstance().getReference("users")
        val query:Query=db.orderByChild("cgpa")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Iterate through the sorted data
                for (snapshot in dataSnapshot.children) {
                    // Get the data from each child node
                    val name = snapshot.child("users").getValue(String::class.java)
                    val score = snapshot.child("cgpa").getValue(Double::class.java)
                    // Do something with the data
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
            }
        })

    }
}
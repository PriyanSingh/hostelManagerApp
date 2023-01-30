package com.example.hotelmanagernith.activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagernith.Models.User

import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.ActivityRegistrationforProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_registrationfor_profile.*
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class RegistrationforProfile : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationforProfileBinding
    private lateinit var database:DatabaseReference
    private lateinit var ImageUri :Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationforProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setOnClickListener{
            selectImage()

        }

        binding.button5.setOnClickListener{
            uploadImage()

            val Name=binding.editTextTextPersonName7.text.toString()
            val RollNo=binding.editTextTextPersonName16.text.toString()
            val Age =binding.editTextTextPersonName12.text.toString()
            val RoomNo=binding.editTextTextPersonName13.text.toString()
            val Adress=binding.editTextTextPersonName15.text.toString()
            val MobileNo=binding.editTextTextPersonName14.text.toString()
            val Imageuri=ImageUri.toString()

            database=FirebaseDatabase.getInstance().getReference("user")
            val User= User(Name,RollNo,Age,RoomNo,Adress,MobileNo,Imageuri)
            database.child(RollNo).setValue(User).addOnSuccessListener {
                binding.editTextTextPersonName7.text.clear()
                binding.editTextTextPersonName16.text.clear()
                binding.editTextTextPersonName12.text.clear()
                binding.editTextTextPersonName13.text.clear()
                binding.editTextTextPersonName15.text.clear()
                binding.editTextTextPersonName14.text.clear()
                Toast.makeText(this,"SuccessFully Saved",Toast.LENGTH_SHORT).show()


            }.addOnFailureListener{
                Toast.makeText(this,"Falied",Toast.LENGTH_SHORT).show()

            }


        }



    }

    private fun uploadImage() {
        val progressDialog =ProgressDialog(this)
        progressDialog.setMessage("Uploading File . . .")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val formatter =SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now=Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference.putFile(ImageUri).
        addOnSuccessListener {
            binding.imageView.setImageURI(null)
            Toast.makeText(this,"Successfully uploaded",Toast.LENGTH_SHORT).show()
            if(progressDialog.isShowing)progressDialog.dismiss()

        }.addOnFailureListener {
            if(progressDialog.isShowing)progressDialog.dismiss()
            Toast.makeText(this,"Failed to Upload Image",Toast.LENGTH_SHORT).show()
        }

    }

    private  fun  selectImage(){
        val intent =  Intent()
        intent.type = "image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
      if(requestCode==100 && resultCode == RESULT_OK){
          ImageUri =data?.data!!
          binding.imageView.setImageURI(ImageUri)


      }
    }
}
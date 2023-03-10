package com.example.hotelmanagernith.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.hotelmanagernith.Models.User
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.FragmentProfileeFragementBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.edit_profile_dialog_box.view.*
import kotlinx.android.synthetic.main.fragment_profilee_fragement.*

class ProfileFragment : Fragment() {
    private lateinit var database:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentProfileeFragementBinding.inflate(layoutInflater)
        database= FirebaseDatabase.getInstance().getReference("name")
        lateinit var name:String
        lateinit var rollNo:String
        lateinit var hostel:String
        lateinit var roomNo:String
        lateinit var adress:String
        lateinit var mobNo:String
        binding.btnEdit.setOnClickListener{
            val dialogBinding=layoutInflater.inflate(R.layout.edit_profile_dialog_box,null)
            val myDialogBinding=Dialog(this@ProfileFragment.requireContext())
            myDialogBinding.setContentView(dialogBinding)
            myDialogBinding.show()


            val save=dialogBinding.findViewById<Button>(R.id.btnSave)
            save.setOnClickListener{
                name=dialogBinding.etName.text.toString()
                rollNo =dialogBinding.etRollno.text.toString()
                hostel =dialogBinding.etHostel.text.toString()
                roomNo=dialogBinding.etRoomNo.text.toString()
                adress=dialogBinding.etAdress.text.toString()
                mobNo=dialogBinding.etMobile.text.toString()
                database=FirebaseDatabase.getInstance().getReference("user")
                val User= User(rollNo,name,hostel,roomNo,adress,mobNo)
                val id=database.push().key!!
                database.child(id).setValue(User).addOnSuccessListener {
                    Toast.makeText(this@ProfileFragment.requireContext(),"Succesfully Saved",Toast.LENGTH_LONG).show()
                }.addOnFailureListener{
                    Toast.makeText(this@ProfileFragment.requireContext(),"Failure",Toast.LENGTH_LONG).show()
                }

                tvName.text=name
                tvRollNo.text=rollNo
                tvHostel.text=hostel
                tvRoomNo.text=roomNo
                tvAddress.text=adress
                tvMobNo.text=mobNo
                tvUserName.text=name
                tvEmail.text=rollNo+"@nith.ac.in"
                myDialogBinding.dismiss()
            }
        }

        return binding.root
    }

}
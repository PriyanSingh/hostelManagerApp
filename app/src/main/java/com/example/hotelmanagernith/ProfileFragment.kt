package com.example.hotelmanagernith

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.hotelmanagernith.databinding.CustomDialogBinding
import com.example.hotelmanagernith.databinding.FragmentProfileeFragementBinding
import kotlinx.android.synthetic.main.edit_profile_dialog_box.*
import kotlinx.android.synthetic.main.edit_profile_dialog_box.view.*
import kotlinx.android.synthetic.main.fragment_profilee_fragement.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentProfileeFragementBinding.inflate(layoutInflater)
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

                tvName.text=name
                tvRollNo.text=rollNo
                tvHostel.text=hostel
                tvRoomNo.text=roomNo
                tvAddress.text=adress
                tvMobNo.text=mobNo
                myDialogBinding.dismiss()
            }
        }

        return binding.root
    }

}
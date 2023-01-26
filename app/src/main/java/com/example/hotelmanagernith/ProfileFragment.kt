package com.example.hotelmanagernith

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.hotelmanagernith.activity.HomeActivity
import com.example.hotelmanagernith.databinding.FragmentProfileeFragementBinding


class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileeFragementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentProfileeFragementBinding.inflate(layoutInflater)
        val context: FragmentActivity? = activity
        binding.tvProfile.setOnClickListener{
            Toast.makeText(requireContext().applicationContext as Activity, " dksnf", Toast.LENGTH_SHORT).show()
        }
        binding.btnEdit.setOnClickListener{
            val dialogBinding=layoutInflater.inflate(R.layout.edit_profile_dialog_box,null)
            Toast.makeText(requireContext().applicationContext as Activity, "lkfs", Toast.LENGTH_SHORT).show()
            val myDialog= Dialog(requireContext().applicationContext as Activity)
            myDialog.setContentView(dialogBinding)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilee_fragement, container, false)
    }
}
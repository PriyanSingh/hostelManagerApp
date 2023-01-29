package com.example.hotelmanagernith

import android.app.DownloadManager.Query
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.Adapter.RoomAdapter
import com.example.hotelmanagernith.Models.RoomData
import com.example.hotelmanagernith.Models.UserViewModel
import com.example.hotelmanagernith.activity.RoomDetails
import com.example.hotelmanagernith.databinding.ActivityHomeBinding
import com.example.hotelmanagernith.databinding.FragmentHostelNameSelectorBinding
import com.google.android.gms.common.util.ArrayUtils.contains
import kotlinx.android.synthetic.main.fragment_hostel_name_selector.*

//import kotlin.collections.EmptyList.contains
//import kotlin.collections.EmptySet.contains


private lateinit var viewModel: UserViewModel
private lateinit var userRecyclerView: RecyclerView
private lateinit var binding:FragmentHostelNameSelectorBinding
lateinit var adapter: RoomAdapter


class HostelNameSelector : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentHostelNameSelectorBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView=view.findViewById(R.id.rvRoomList)
        userRecyclerView.layoutManager=LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter= RoomAdapter(this@HostelNameSelector.requireContext())
        userRecyclerView.adapter= adapter

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            adapter.updateUserList(it)
        })

    }
}
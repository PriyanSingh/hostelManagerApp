package com.example.hotelmanagernith.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.Adapter.RoomAdapter
import com.example.hotelmanagernith.Models.UserViewModel
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.FragmentHostelNameSelectorBinding


private lateinit var viewModel: UserViewModel
private lateinit var userRecyclerView: RecyclerView
private lateinit var binding:FragmentHostelNameSelectorBinding
lateinit var adapter: RoomAdapter


class RoomSearch : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHostelNameSelectorBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView=view.findViewById(R.id.rvRoomList)
        userRecyclerView.layoutManager=LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter= RoomAdapter(this@RoomSearch.requireContext())
        userRecyclerView.adapter= adapter

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            adapter.updateUserList(it)
        })

    }
}
package com.example.hotelmanagernith

import android.app.DownloadManager.Query
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
import com.example.hotelmanagernith.databinding.ActivityHomeBinding
import com.example.hotelmanagernith.databinding.FragmentHostelNameSelectorBinding
import com.google.android.gms.common.util.ArrayUtils.contains
//import kotlin.collections.EmptyList.contains
//import kotlin.collections.EmptySet.contains


private lateinit var viewModel: UserViewModel
private lateinit var userRecyclerView: RecyclerView
private lateinit var binding:FragmentHostelNameSelectorBinding
lateinit var adapter: RoomAdapter


class HostelNameSelector : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentHostelNameSelectorBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HostelNameSelector.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HostelNameSelector().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView=view.findViewById(R.id.rvRoomList)
        userRecyclerView.layoutManager=LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter= RoomAdapter()
        userRecyclerView.adapter= adapter

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            adapter.updateUserList(it)
        })

    }
}
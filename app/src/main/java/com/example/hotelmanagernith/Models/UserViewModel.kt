package com.example.hotelmanagernith.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotelmanagernith.RoomRepository

class UserViewModel:ViewModel() {

    private val repository:RoomRepository
    private val _allRoom= MutableLiveData<List<RoomData>>()
    val allUsers: LiveData<List<RoomData>> =_allRoom

    init {
        repository=RoomRepository().getInstance()
        repository.loadUsers(_allRoom)
    }

}
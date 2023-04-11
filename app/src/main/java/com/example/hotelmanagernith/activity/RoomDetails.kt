package com.example.hotelmanagernith.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagernith.Adapter.roomiesAdapter
import com.example.hotelmanagernith.R
import com.example.hotelmanagernith.databinding.ActivityRoomDetails2Binding
import com.google.android.gms.tasks.Tasks
import com.google.common.primitives.UnsignedBytes.toInt
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_room_details2.*
import kotlinx.android.synthetic.main.room_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RoomDetails : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var binding: ActivityRoomDetails2Binding
    private lateinit var newArrayList: ArrayList<String>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var roomNo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        roomNo = intent.getStringExtra("Roomno").toString()
        binding.tvRoom.text=roomNo
        databaseRef =
            FirebaseDatabase.getInstance().getReference("rooms")
        newArrayList = arrayListOf<String>()
        var capacity =0

        var count=0
        binding.btnSubmit.isEnabled=false
        val eightDigitStrings: MutableList<String> = mutableListOf()

        binding.button3.setOnClickListener {
            val rollno = binding.textView6
            if (rollno.text.isNotEmpty()) {
                lifecycleScope.launch {
                    try {
                        val capacity = getCapacity()
                        Log.i("firebase1", "Capacity: $capacity")
                        if(count<capacity) {
                            newArrayList.add(rollno.text.toString())
                            newRecyclerView.adapter?.notifyItemInserted(newArrayList.size - 1)
                            eightDigitStrings.add(rollno.text.toString())
                            rollno.text.clear()
                            count++

                            closeKeyboard(rollno)
                            if(count==capacity){
                                binding.btnSubmit.isEnabled=true
                            }
                        }
                        else{
                            Toast.makeText(this@RoomDetails, "Maximum limit reached", Toast.LENGTH_SHORT).show()
                        }
                        // update UI with capacity
                    } catch (e: Exception) {
                        Log.e("firebase", "Error getting capacity", e)
                        // handle error
                    }
                }
                Log.i("firebase0", "Capacity: $capacity")

            }
        }
        binding.btnSubmit.setOnClickListener {

            val capacitySnapshot = databaseRef.child(roomNo.toString()).child("boarders").setValue(eightDigitStrings)
                .addOnSuccessListener {
                // Write operation successful, show success message
                    val capacitySnapshot = databaseRef.child(roomNo.toString()).child("count").setValue(count)
                    Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                // Write operation failed, show error message
                Toast.makeText(this, "Error while saving data ${it.message}", Toast.LENGTH_SHORT).show()
            }

        }
        newRecyclerView = findViewById(R.id.rvRoomie)
        newRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        newRecyclerView.setHasFixedSize(false)
        newRecyclerView.adapter = roomiesAdapter(newArrayList)
    }


    suspend fun getCapacity(): Int = withContext(Dispatchers.IO) {
        Log.i("firebase0", "RoomNo: $roomNo")
        val capacitySnapshot = databaseRef.child(roomNo.toString()).child("capacity").get().await()
        val capacity = capacitySnapshot.getValue(String::class.java)?.toInt() ?: 0
        return@withContext capacity
    }

    private fun closeKeyboard(rollno: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(rollno.windowToken, 0)
    }
}
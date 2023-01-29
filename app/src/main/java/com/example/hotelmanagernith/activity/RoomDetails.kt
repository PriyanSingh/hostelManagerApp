package com.example.hotelmanagernith.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hotelmanagernith.R

class RoomDetails : AppCompatActivity() {
    lateinit var button: Button
    lateinit var textView6:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details2)
        button=findViewById(R.id.button3)
        textView6=findViewById(R.id.textView6)
        showEditTextDialog()

    }
        private fun showEditTextDialog(){
        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val inflater =layoutInflater



            val dialogLayout = inflater.inflate(R.layout.room_allocation_details,null)
            val editText=dialogLayout.findViewById<EditText>(R.id.editTextTextPersonName4)

            with(builder){
                setTitle("Enter Your ROLL_NO")
                setPositiveButton("OK"){dialog,which->

                    

                    textView6.text=editText.text.toString()
                }
                setNegativeButton("Cancel"){dialog,which->
                    Log.d("Main","Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

    }
}
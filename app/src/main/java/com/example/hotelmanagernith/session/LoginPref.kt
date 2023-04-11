package com.example.hotelmanagernith.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.hotelmanagernith.activity.LoginActivity

class LoginPref {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATEMODE: Int = 0

    constructor(con: Context){
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME = "Login_Preference"
        val IS_LOGIN = "isLoggedIn"
        val KEY_USERNAME = "username"
        val KEY_EMAIL = "email"
    }

    fun createLoginSession(username: String, email: String){
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_EMAIL,email)
        editor.commit()
    }

    private fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun checkLogin(){
        if (!this.isLoggedIn()){
            var i: Intent = Intent(con,LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String>{
        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(KEY_USERNAME,pref.getString(KEY_USERNAME,null)!!)
        return user
    }

    fun LogoutUser(){
        editor.clear()
        editor.commit()
        var i : Intent = Intent(con,LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }
}
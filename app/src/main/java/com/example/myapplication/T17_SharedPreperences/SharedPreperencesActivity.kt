package com.example.myapplication.T17_SharedPreperences

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class SharedPreperencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preperences)



        val pref = getSharedPreferences("mySettings",0)
        val str = pref.getString("name", "empty")
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        val pref = getSharedPreferences("mySettings",0)
        val editor = pref.edit()
        editor.putString("name", "hello world")
        editor.apply()
    }


}

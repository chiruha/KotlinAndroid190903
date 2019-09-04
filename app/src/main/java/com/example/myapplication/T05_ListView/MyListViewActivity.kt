package com.example.myapplication.T05_ListView

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_my_list_view.*

class MyListViewActivity : AppCompatActivity() {

    val myData = arrayOf("hello1", "world2", "android3","kotlin4",
        "hello5", "world6", "android7","kotlin8",
        "hello9", "world10", "android11","kotlin12",
        "hello13", "world14", "android15","kotlin16")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list_view)

        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, myData)
        myListView.adapter = adapter

        myListView.setOnItemClickListener{adapterView, view, position, l ->
            val str = myData[position]
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }
    }
}

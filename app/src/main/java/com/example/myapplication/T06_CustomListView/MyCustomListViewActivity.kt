package com.example.myapplication.T06_CustomListView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MyCustomListViewActivity : AppCompatActivity() {
    data class MyData (val title: String, val desc:String, val img: Int)
    val myList = ArrayList<MyData>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_list_view)
        generateData()
    }

    private fun generateData() {
        for(i in 0..100) {
            myList.add(MyData("title $i","desc $i", R.drawable.ic_3d_rotation_black_24dp))
        }
    }
}

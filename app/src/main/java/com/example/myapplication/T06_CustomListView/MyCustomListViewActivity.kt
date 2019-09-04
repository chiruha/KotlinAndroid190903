package com.example.myapplication.T06_CustomListView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_my_custom_list_view.*

class MyCustomListViewActivity : AppCompatActivity() {
    data class MyData (val title: String, val desc:String, val img: Int)
    val myList = ArrayList<MyData>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_list_view)
        generateData()
        myCustomListView.adapter = MyCustomAdapter()
    }

    private fun generateData() {
//        val icons = arrayOf(R.drawable.ic_3d_rotation_black_24dp,
//            R.drawable.ic_ac_unit_black_24dp,
//            R.drawable.ic_access_alarm_black_24dp)
//
//        for(i in 0..100) {
//            val icon = icons[i%3]
//
//            myList.add(MyData("title $i","desc $i", icon))
//        }
    }



    inner class MyCustomAdapter: BaseAdapter() { // 자신을 포함하고 있는 클래스 사용을 위해 inner 적음
        override fun getView(position: Int, converterView: View?, parent: ViewGroup?): View {
            val v = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.activity_my_custom_list_view, parent, false)

            // val textViewTitle = v.findViewById<TextView>()
            val textViewTitle: TextView = v.findViewById(R.id.titleTextView)
            val textViewDesc: TextView = v.findViewById(R.id.descTextView)
            val itemImageView: ImageView = v.findViewById(R.id.itemImageView)

            val data = myList[position]
            textViewTitle.text = data.title
            textViewDesc.text = data.desc
            itemImageView.setImageResource(data.img)

            return v
        }

        override fun getItem(position: Int): Any {
            return myList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return myList.size
        }

    }
}

package com.example.myapplication.T16_RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

// VH 는 View Holder Class
class MyRecyclerViewAdapter(val myList: ArrayList<RecyclerViewActivity.MyData>)
    : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = myList[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_listview, parent, false)
        return MyViewHolder(itemView)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var data: RecyclerViewActivity.MyData
        fun bind(data: RecyclerViewActivity.MyData) {
            titleTextView.text = data.title
            descTextView.text = data.desc
            itemImageView.setImageResource(data.img)
        }


        val itemImageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descTextView: TextView = itemView.findViewById(R.id.descTextView)

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, data.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
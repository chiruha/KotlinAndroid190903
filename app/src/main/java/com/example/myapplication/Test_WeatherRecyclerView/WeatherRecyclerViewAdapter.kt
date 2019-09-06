package com.example.myapplication.Test_WeatherRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class WeatherRecyclerViewAdapter (
    val weatherList: ArrayList<WeatherRecyclerViewActivity.WeatherData>)
    : RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherHolder>() {

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val data = weatherList[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_listview, parent, false)
        return WeatherHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }


    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var data: WeatherRecyclerViewActivity.WeatherData
        fun bind(data: WeatherRecyclerViewActivity.WeatherData) {
            this.data = data
            titleTextView.text = data.wfKor
            descTextView.text = "${data.hour} : ${data.temp}: ${data.day}"

            Log.d("myTest :: ","============================ ${data}")

        }

        val itemImageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descTextView: TextView = itemView.findViewById(R.id.descTextView)

        init{
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, data.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}
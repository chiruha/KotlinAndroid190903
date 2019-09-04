package com.example.myapplication.T11_ImageView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        // https://api.androidhive.info/music/images/adele.png

        val path = "https://api.androidhive.info/music/images/adele.png"
        Glide.with(this).load(path).into(myImageView)
    }
}

package com.example.myapplication.T04_Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_my_activity.*

class MyActivity : AppCompatActivity() {

    // private static final int REQ_CODE = 100
    private val REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_activity)

        btnStart.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            intent.putExtra("test",123)
            //startActivity(intent)
            startActivityForResult(intent, REQ_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQ_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    val str = data.getStringExtra("resValue")
                }
                // 또는
                val str = data?.getStringExtra("resValue")
            }






        }
    }
}

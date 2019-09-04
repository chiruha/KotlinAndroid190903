package com.example.myapplication.T04_Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val value = intent.getIntExtra("hello",0)
        btnFinish.setOnClickListener {
            val resIntent = Intent()
            resIntent.putExtra("resValue", "hello world")
            setResult(Activity.RESULT_OK, resIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}

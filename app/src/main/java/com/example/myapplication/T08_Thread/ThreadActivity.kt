package com.example.myapplication.T08_Thread

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_thread.*

class ThreadActivity : AppCompatActivity() {

    private val MY_COUNT = 100;

    val handler = @SuppressLint("HandlerLeak")
    object: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == MY_COUNT) {
                btnStart.setText("count ${msg.arg1}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        btnStart.setOnClickListener {
            Thread{
                for(i in 0..100) {
                    Log.d("thread", "count :: ${i}")
                    //btnStart.setText("count ${i}") // ui에 접근불가 -> handler 를 통해야함
                    Thread.sleep(100)

                    val msg = handler.obtainMessage()
                    msg.what = MY_COUNT
                    msg.arg1 = i

                    handler.sendMessage(msg)
                }
            }.start()
        }



    }
}

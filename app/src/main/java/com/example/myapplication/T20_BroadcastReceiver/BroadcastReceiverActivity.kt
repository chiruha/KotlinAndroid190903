package com.example.myapplication.T20_BroadcastReceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_broadcast_receiver.*

class BroadcastReceiverActivity : AppCompatActivity() {


    lateinit var receiver: MyReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_broadcast_receiver)

        btnSend.setOnClickListener {
            val intent = Intent()
            intent.action = "hello.world.android.kotlin"
            sendBroadcast(intent)
        }

        receiver = MyReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("hello.world.android.kotlin")
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


}

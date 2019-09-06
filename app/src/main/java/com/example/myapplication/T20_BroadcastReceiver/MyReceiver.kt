package com.example.myapplication.T20_BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "hello airplane  mode",
            Toast.LENGTH_SHORT).show()
    }
}

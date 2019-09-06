package com.example.myapplication.T19_Service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class MyBindTypeService : Service() {

    inner class MyBinder: Binder() {
        fun getService() : MyBindTypeService {
            return this@MyBindTypeService
        }
    }

    private val myBinder = MyBinder()
    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    private val rand = Random()

    fun getRandomNumber() : Int {
        return rand.nextInt(100)

    }




}
package com.example.myapplication.T18_SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, age INTEGER, address TEXT)"
        db?.execSQL(sql)
    }

    // 버전 다를시 한 번만 호출
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sql = "DROP TABLE IF EXISTS student"
        db?.execSQL(sql)
        onCreate(db)
    }
}

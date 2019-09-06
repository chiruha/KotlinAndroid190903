package com.example.myapplication.T18_SQLite

import android.content.ContentValues
import android.content.Context

class MyDBHandler(context: Context) {
    val helper : MyOpenHelper = MyOpenHelper(context, "peple.db", null, 1)

    fun insert(name: String, age: Int, address: String) {
        val values = ContentValues()
        values.put("name", name)
        values.put("age", age)
        values.put("address", address)
        helper.writableDatabase.insert("student", null, values)

    }

    fun delete(name: String) {
        helper.writableDatabase.delete("student", "name = ? and age = ?", arrayOf(name))
    }

    fun update(name: String, newAge: Int) {
        val values = ContentValues()
        values.put("age", newAge)
        helper.writableDatabase.update("student", values, "name = ?", arrayOf(name))
    }

    fun selectAll() : String {
        var res = ""
        val cursor =  helper.readableDatabase.query("student", null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val name = cursor.getString( cursor.getColumnIndex("name") )
            val age = cursor.getInt( cursor.getColumnIndex("age") )
            val address = cursor.getString( cursor.getColumnIndex("address") )
            res += "name: ${name} age: ${age} address ${address} \n"

        }
        return res
    }

}
package com.example.myapplication.T07_AlertDialog

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        val dialogView = layoutInflater.inflate(R.layout.dialog_view,null)
        val editText = dialogView.findViewById<EditText>(R.id.myDialogEditText)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.myDialogRatingBar)
        val builder = AlertDialog.Builder(this)
        builder
            .setView(dialogView)
            .setTitle("hello title")
            .setMessage("hello message")
            .setPositiveButton("OK") { dialog, which ->
                Toast.makeText(this,
                    "positive button clicked ${editText.text} , ${ratingBar.rating}",
                    Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") {dialog, i ->

            }

        builder.show()

    }






}

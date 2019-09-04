package com.example.myapplication.T03_UIWidget

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_uiwidget.*

class UIWidgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiwidget)

        btnHello.setOnClickListener {
            val myText = editText.text.toString()
            Toast.makeText(this, "$myText", Toast.LENGTH_SHORT).show()
            editText.setText("")
        }
        
        myCheckBox.setOnCheckedChangeListener { compoundButton, checked ->
            Toast.makeText(this, "checked $checked", Toast.LENGTH_SHORT).show()
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            var text: String = ""
            when(id) {
                R.id.radio1 -> { text = "radion1" }
                R.id.radio2 -> { text = "radion2" }
                R.id.radio3 -> { text = "radion3" }
            }
            Toast.makeText(this, "radio :  $text is clicked", Toast.LENGTH_SHORT).show()

        }


    }
}

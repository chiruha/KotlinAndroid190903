package com.example.myapplication.T13_RunTimePermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import gun0912.tedbottompicker.TedBottomPicker
import kotlinx.android.synthetic.main.activity_permission.*


class PermissionActivity : AppCompatActivity() {

    private val PERMISSION_REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        btnPermission.setOnClickListener {
            // setupPermission()

            var permission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if(permission != PackageManager.PERMISSION_GRANTED){
                setupPermission()
            }else{
                imagePick()
            }
        }


    }

    private fun imagePick() {
        TedBottomPicker.with(this)
            .show{
                myImageView.setImageURI(it)
                //Glide.with(this).load(it).into(myImageView)
            }
    }

    private fun setupPermission() {
        var permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permission != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale( // shouldShowRequestPermissionRationale 한 번 거절했을 시
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )) {
                AlertDialog.Builder(this)
                    .setTitle("Permission requested!!")
                    .setMessage("Permission to write external storage is required for this app!!!!")
                    .setPositiveButton("OK") {dialogInterface, which ->
                        requestPermission()
                    }.show()
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        // request 할 때는 array 형태로 받음
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQ_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_REQ_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imagePick()
            } else {

            }
        }
    }



}

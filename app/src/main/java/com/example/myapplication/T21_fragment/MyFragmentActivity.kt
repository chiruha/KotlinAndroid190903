package com.example.myapplication.T21_fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_fragment.*

class MyFragmentActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        val fragment = supportFragmentManager.findFragmentById(R.id.containerLayout)
        when(view?.id){
            R.id.btnAdd->{
                if(fragment == null){
                    val newFragment = CounterFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.containerLayout, newFragment, "counter")
                        .addToBackStack(null)
                        .commit()
                }
            }
            R.id.btnRemove->{
                if(fragment != null){
                    supportFragmentManager
                        .beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            }
            R.id.btnReplace->{
                if(fragment != null){
                    val newFragment = if(fragment.tag.equals("counter")){
                        BlankFragment()
                    }else{
                        CounterFragment()
                    }
                    val tag = if(newFragment is CounterFragment) "counter" else "blank"

                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerLayout, newFragment, tag)
                        .addToBackStack(null)
                        .commit()
                }
            }
            R.id.btnHide->{
                if(fragment != null){
                    if(fragment.isHidden) {
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragment)
                            .commit()
                    }else{
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragment)
                            .commit()
                    }
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        btnAdd.setOnClickListener (this)
        btnRemove.setOnClickListener (this)
        btnReplace.setOnClickListener (this)
        btnHide.setOnClickListener (this)

    }



}

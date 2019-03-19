package com.example.tutienda.register.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.tutienda.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment:Fragment? =fragmentManager.findFragmentById(R.id.flRegister)

        if(fragment==null){
            fragment=RegisterFragment()
            fragmentManager.beginTransaction().replace(R.id.flRegister,fragment).commit()
        }


    }
}

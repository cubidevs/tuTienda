package com.example.tutienda.login.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.tutienda.R
import com.example.tutienda.register.ui.RegisterFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment: Fragment? =fragmentManager.findFragmentById(R.id.flLogin)

        if(fragment==null){
            fragment= LoginFragment()
            fragmentManager.beginTransaction().replace(R.id.flLogin,fragment).commit()
        }
    }
}

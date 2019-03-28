package com.example.tutienda.login.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.tutienda.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.flLogin)

        if (fragment == null) {
            fragment = LoginFragment()
            fragmentManager.beginTransaction().replace(R.id.flLogin, fragment).commit()
        }
    }
}

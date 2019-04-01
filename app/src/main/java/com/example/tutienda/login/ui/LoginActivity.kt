package com.example.tutienda.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tutienda.R
import android.view.WindowManager
import android.os.Build

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getColor(R.color.colorPrimaryDark)
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.flLogin)

        if (fragment == null) {
            fragment = LoginFragment()
            fragmentManager.beginTransaction().replace(R.id.flLogin, fragment).commit()
        }
    }
}

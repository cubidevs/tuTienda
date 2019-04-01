package com.example.tutienda.register.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.example.tutienda.R
import com.example.tutienda.utils.Constants

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getColor(R.color.colorPrimaryDark)
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.flRegister)

        if (fragment == null) {
            fragment = RegisterFragment()
            fragmentManager.beginTransaction().replace(R.id.flRegister, fragment).commit()
        }
        checkSternalStoragePermission()
    }

    private fun checkSternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (result == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.REQUEST_STORAGE
                )
            } else if (result == PackageManager.PERMISSION_GRANTED) {
                val resultPhone = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if (resultPhone == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        Constants.REQUEST_PHONE
                    )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_STORAGE) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {

            } else {
                checkSternalStoragePermission()
            }
        }
    }
}

package com.example.tutienda.utils

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

class IntentHelper{

    fun goToMainActivity(context: Context, destination: Class<*>){
        val intent = Intent(context,destination)
        startActivity(context,intent,null)
    }
}

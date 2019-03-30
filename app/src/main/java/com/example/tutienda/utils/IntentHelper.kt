package com.example.tutienda.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class IntentHelper{

    fun goToMainActivity(context: Context, destination: Class<*>){
        val intent = Intent(context,destination)
        startActivity(context,intent,null)
    }
    fun goToLoginActivity(context: Context, destination: Class<*>){
        val intent = Intent(context,destination)
        startActivity(context,intent,null)
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}

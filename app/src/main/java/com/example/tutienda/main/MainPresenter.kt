package com.example.tutienda.main

import androidx.appcompat.app.AlertDialog
import com.example.tutienda.R
import com.example.tutienda.login.ui.LoginActivity
import com.example.tutienda.utils.IntentHelper
import com.google.firebase.auth.FirebaseAuth

class MainPresenter(private var view: IMainMVP.view) :IMainMVP.presenter {

    override fun validateUserLog() {
            val uid= FirebaseAuth.getInstance().uid
            if(uid==null){
                IntentHelper().goToLoginActivity(view.getContext(), LoginActivity::class.java)
                view.outApp()
            }
    }

    override fun closeSession() {
        val builder = AlertDialog.Builder(view.getContext())
        builder.setTitle(R.string.close_session)
        builder.setMessage(R.string.lab_dialog_logout_confirmation_message)

        builder.setPositiveButton(R.string.lab_dialog_confirmation_message){dialog, which ->
            FirebaseAuth.getInstance().signOut()
            IntentHelper().goToLoginActivity(view.getContext(), LoginActivity::class.java)
            view.outApp()
        }

        builder.setNegativeButton(R.string.lab_dialog_cancel_message){dialog,which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

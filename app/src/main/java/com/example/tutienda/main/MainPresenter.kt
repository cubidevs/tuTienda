package com.example.tutienda.main

import com.example.tutienda.login.ui.LoginActivity
import com.example.tutienda.utils.IntentHelper
import com.google.firebase.auth.FirebaseAuth

class MainPresenter(private var view: IMainMVP.view) :IMainMVP.presenter {

    override fun validateUserLog() {
            val uid= FirebaseAuth.getInstance().uid
            if(uid==null){
                IntentHelper().goToLoginActivity(view.getContext(), LoginActivity::class.java)
            }
    }

    override fun closeSession() {
        FirebaseAuth.getInstance().signOut()
        IntentHelper().goToLoginActivity(view.getContext(), LoginActivity::class.java)
    }
}

package com.example.tutienda.login

import android.content.Context
import android.view.View
import com.example.tutienda.register.model.User

interface ILoginMVP {

    interface view{
        fun getPassword(): String
        fun getEmail(): String
        fun getView(): View
        fun showError(errorMessage: String)
        fun navigateToMainActivity()
        fun showProgressView()
        fun hideProgressView()
        fun showWelcomeMessage()
        fun getContext(): Context
    }

    interface presenter{
        fun loginButtonClicked()
        fun loginSuccesfull()
        fun sendMessageError(errorMessage: String)
        fun sendContext(): Context
    }

    interface model{
        fun sendCredentials(email: String, password: String)
        fun loginSuccesfull()
        fun sendMessageError(errorMessage: String)
        fun saveUser(user: User)
    }
}

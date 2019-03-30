package com.example.tutienda.login

import android.view.View

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
    }

    interface presenter{
        fun loginButtonClicked()
        fun loginSuccesfull()
        fun sendMessageError(errorMessage: String)
    }

    interface model{
        fun sendCredentials(email: String, password: String)
        fun loginSuccesfull()
        fun sendMessageError(errorMessage: String)
    }
}

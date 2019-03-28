package com.example.tutienda.login

import android.view.View

interface ILoginMVP {

    interface view{
        fun getPassword(): String
        fun getEmail(): String
        fun getView(): View
        fun showError(errorMessage: String)
        fun navigateToMainActivity()
    }

    interface presenter{
        fun loginButtonClicked()
        fun loginSuccesfull()
    }

    interface model{
        fun sendCredentials(email: String, password: String)
        fun loginSuccesfull()
    }
}

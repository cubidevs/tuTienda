package com.example.tutienda.register

import android.view.View

interface IRegisterMVP {

    interface view{
        fun getFullName(): String
        fun getEmail(): String
        fun getCell(): String
        fun getPassword(): String
        fun getRepeatPassword(): String
        fun getView(): View
        fun showError(errorMessage: String)
        fun showSucces(succesMessage:String)
    }
    interface presenter{
        fun registerButtonClicked()

    }

    interface model{

    }
}
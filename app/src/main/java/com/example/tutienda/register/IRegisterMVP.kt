package com.example.tutienda.register

interface IRegisterMVP {

    interface view{
        fun getFullName(): String
        fun getEmail(): String
        fun getCell(): String
        fun getPassword(): String
        fun getRepeatPassword(): String
        fun showError(errorMessage: String)
    }
    interface presenter{
        fun registerButtonClicked()

    }

    interface model{

    }
}
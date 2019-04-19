package com.example.tutienda.main

import android.content.Context

interface IMainMVP {

    interface view{
        fun getContext():Context
        fun outApp()
        fun showUser(fullName:String, urlPhoto: String, email: String)
    }

    interface presenter{
        fun validateUserLog()
        fun closeSession()
        fun loadUser()
    }
    interface model{
    }
}
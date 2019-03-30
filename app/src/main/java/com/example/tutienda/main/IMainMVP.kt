package com.example.tutienda.main

import android.content.Context

interface IMainMVP {

    interface view{
        fun getContext():Context
    }

    interface presenter{
        fun validateUserLog()
        fun closeSession()
    }
}
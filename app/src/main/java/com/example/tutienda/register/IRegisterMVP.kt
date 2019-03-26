package com.example.tutienda.register

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import com.example.tutienda.register.model.User

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
        fun getConditions():Boolean
        fun getPhoto(): Bitmap
        fun getContext(): Context
    }

    interface presenter{
        fun registerButtonClicked()
        fun getUriPhoto()
    }

    interface model{
        fun sendUser(fullName: String, email: String, cell: String, password: String, urlPhoto: Uri)
        fun userCreated(user: User,uriPhoto:Uri)
    }
}

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
        fun getCellPhone(): String
        fun getPassword(): String
        fun getRepeatPassword(): String
        fun getView(): View
        fun getConditions():Boolean
        fun getPhoto(): Bitmap?
        fun getContext(): Context
        fun showError(errorMessage: String)
        fun showSucces(succesMessage:String)
        fun showProgressView()
        fun hideProgressView()
    }

    interface presenter{
        fun registerButtonClicked()
        fun getUriPhoto()
        fun userSavedInDataBase()
        fun sendMessageError(errorMessage: String)
    }

    interface model{
        fun sendUser(fullName: String, email: String, cellphone: String, password: String, urlPhoto: Uri?)
        fun userCreated(user: User,uriPhoto:Uri?)
        fun photoSaved(urlPhoto: String, user: User)
        fun userSavedInDataBase()
        fun sendMessageError(errorMessage: String)
    }
}

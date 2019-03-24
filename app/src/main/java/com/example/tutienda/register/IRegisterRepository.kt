package com.example.tutienda.register

import android.graphics.Bitmap
import android.net.Uri
import com.example.tutienda.register.model.User

interface IRegisterRepository {
    fun createUser(user: User)
    fun savePhoto(urlPhoto: Uri)

}
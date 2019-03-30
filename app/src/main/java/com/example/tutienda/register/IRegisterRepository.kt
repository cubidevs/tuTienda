package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User

interface IRegisterRepository {

    fun createUser(user: User, password: String, urlPhoto: Uri?)
    fun savePhoto(urlPhoto: Uri?, user: User)
    fun saveUserInDataBase(urlPhoto: String, user: User)

}

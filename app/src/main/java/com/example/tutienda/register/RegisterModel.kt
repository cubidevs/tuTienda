package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User

class RegisterModel:IRegisterMVP.model {

    private lateinit var presenter:IRegisterMVP.presenter
    private lateinit var repository:IRegisterRepository
    private var uriPhoto:Uri?=null
    lateinit var user: User

    constructor(presenter: IRegisterMVP.presenter){
        this.presenter=presenter
        repository=RegisterRepository(this)
    }
    override fun sendUser(fullName: String, email: String, cell: String, password: String, uriPhoto: Uri?) {
        this.uriPhoto= uriPhoto
        user=User(fullName,email,cell,password)
        repository.createUser(user, uriPhoto)
    }
    override fun userCreated(user: User, uriPhoto: Uri?) {
        repository.savePhoto(uriPhoto,user)
    }
}

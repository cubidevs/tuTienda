package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User

class RegisterModel:IRegisterMVP.model {

    private lateinit var presenter:IRegisterMVP.presenter
    private lateinit var repository:IRegisterRepository
    private lateinit var urlPhoto:Uri

    constructor(presenter: IRegisterMVP.presenter){
        this.presenter=presenter
        repository=RegisterRepository(this)

    }
    override fun sendUser(fullName: String, email: String, cell: String, password: String, urlPhoto: Uri) {
        this.urlPhoto=urlPhoto
        val user:User=User(fullName,email,cell,password)
        repository.createUser(user)


    }
    override fun userCreated(user: User) {
        repository.savePhoto(urlPhoto)
    }

}
package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User

class RegisterModel:IRegisterMVP.model {

    private var presenter:IRegisterMVP.presenter
    private var repository:IRegisterRepository
    private var uriPhoto:Uri? = null
    lateinit var user: User

    constructor(presenter: IRegisterMVP.presenter){
        this.presenter=presenter
        repository=RegisterRepository(this)
    }

    override fun sendUser(fullName: String, email: String, cellphone: String, password: String, uriPhoto: Uri?) {
        this.uriPhoto=uriPhoto
        user=User(fullName,email,cellphone)
        repository.createUser(user,password,uriPhoto)
    }

    override fun userCreated(user: User, uriPhoto: Uri?) {
        repository.savePhoto(uriPhoto,user)
    }

    override fun photoSaved(urlPhoto: String, user: User) {
        repository.saveUserInDataBase(urlPhoto, user)
    }

    override fun userSavedInDataBase() {
        presenter.userSavedInDataBase()
    }

    override fun sendMessageError(errorMessage: String) {
        presenter.sendMessageError(errorMessage)
    }
}

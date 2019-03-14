package com.example.tutienda.register

class RegisterModel:IRegisterMVP.model {

    private lateinit var presenter:IRegisterMVP.presenter
    private lateinit var repository:IRegisterRepository

    constructor(presenter: IRegisterMVP.presenter){
        this.presenter=presenter
        repository=RegisterRepository(this)
    }

}
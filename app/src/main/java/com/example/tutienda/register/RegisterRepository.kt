package com.example.tutienda.register

class RegisterRepository:IRegisterRepository{
    private lateinit var model:IRegisterMVP.model

    constructor(model:IRegisterMVP.model){
        this.model=model
    }
}
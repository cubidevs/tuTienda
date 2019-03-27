package com.example.tutienda.register.model

class User (var fullName:String, var email:String, var cellPhone:String, var password:String){

    var urlPhoto=""

    constructor( fullName:String,  email:String,  cellPhone:String,  password:String, urlPhoto:String) : this(fullName,email,cellPhone,password){
        this.urlPhoto=urlPhoto
    }
}

package com.example.tutienda.login

interface ILoginRepository {

    fun login(email: String, password: String)
}

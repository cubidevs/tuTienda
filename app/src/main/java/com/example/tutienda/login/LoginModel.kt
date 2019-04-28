package com.example.tutienda.login

import com.example.tutienda.data.SessionManager
import com.example.tutienda.register.model.User

class LoginModel(private var presenter: ILoginMVP.presenter) : ILoginMVP.model {

    private var repository: ILoginRepository = LoginRepository(this)
    override fun sendCredentials(email: String, password: String) {
        repository.login(email,password)
    }

    override fun loginSuccesfull() {
        presenter.loginSuccesfull()
    }

    override fun sendMessageError(errorMessage: String) {
        presenter.sendMessageError(errorMessage)
    }

    override fun saveUser(user: User) {
        SessionManager(presenter.sendContext()).saveUserLogin(user)
    }
}

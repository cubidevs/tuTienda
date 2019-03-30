package com.example.tutienda.login

class LoginModel : ILoginMVP.model {

    private var repository: ILoginRepository
    private var presenter: ILoginMVP.presenter

    constructor(presenter: ILoginMVP.presenter){
        this.presenter = presenter
        repository = LoginRepository(this)
    }

    override fun sendCredentials(email: String, password: String) {
        repository.login(email,password)
    }

    override fun loginSuccesfull() {
        presenter.loginSuccesfull()
    }

    override fun sendMessageError(errorMessage: String) {
        presenter.sendMessageError(errorMessage)
    }
}

package com.example.tutienda.login

import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.utils.Constants

class LoginPresenter : ILoginMVP.presenter {

    private var view : ILoginMVP.view
    private var model : ILoginMVP.model

    constructor(view: ILoginMVP.view){
        this.view = view
        model = LoginModel(this)
    }

    override fun loginButtonClicked() {
        view.showProgressView()
        var validateCode= ValidateFields().getValideLogin(view.getEmail(),view.getPassword())

        if (validateCode == Constants.CORRECT_DATA){
            model.sendCredentials(view.getEmail(),view.getPassword())
        }else{
            ValidateFields().setErrorField(validateCode, view.getView())
            view.hideProgressView()
        }
    }

    override fun loginSuccesfull() {
        view.navigateToMainActivity()
        view.hideProgressView()
        view.showWelcomeMessage()
    }

    override fun sendMessageError(errorMessage: String) {
        view.showError(errorMessage)
        view.hideProgressView()
    }
}

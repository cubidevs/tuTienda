package com.example.tutienda.login

import com.example.tutienda.Util.ValidateFields

class LoginPresenter : ILoginMVP.presenter {

    private var view : ILoginMVP.view
    private var model : ILoginMVP.model

    constructor(view: ILoginMVP.view){
        this.view = view
        model = LoginModel(this)
    }

    override fun loginButtonClicked() {
        var validateCode= ValidateFields().getValideLogin(view.getEmail(),view.getPassword())

        if (validateCode == ValidateFields().CORRECT_DATA){
            model.sendCredentials(view.getEmail(),view.getPassword())
        }else{
            ValidateFields().setErrorField(validateCode, view.getView())
        }
    }

    override fun loginSuccesfull() {
        view.navigateToMainActivity()
    }
}

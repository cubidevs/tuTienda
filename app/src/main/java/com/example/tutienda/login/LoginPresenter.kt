package com.example.tutienda.login

import android.content.Context
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.data.SessionManager
import com.example.tutienda.root.TuTienda
import com.example.tutienda.utils.Constants

class LoginPresenter(private var view: ILoginMVP.view) : ILoginMVP.presenter {

    private var model: ILoginMVP.model = LoginModel(this)

    override fun loginButtonClicked() {
        view.showProgressView()
        var validateCode = ValidateFields().getValideLogin(view.getEmail(), view.getPassword())

        if (validateCode == Constants.CORRECT_DATA) {
            model.sendCredentials(view.getEmail(), view.getPassword())
        } else {
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

    override fun sendContext(): Context {
        return view.getContext()
    }
}

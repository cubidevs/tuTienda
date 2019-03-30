package com.example.tutienda.login

import com.example.tutienda.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class LoginRepository : ILoginRepository {

    private var model: ILoginMVP.model
    private var mAuth: FirebaseAuth? = null

    constructor(model: ILoginMVP.model) {
        this.model = model
    }

    override fun login(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    model.loginSuccesfull()
                } else {
                    var errorMessage = Constants.EMPTY
                    if (task.exception.toString().equals(Constants.ERROR_LOGIN_PASSWORD_INVALID_FIREBASE)) {
                        errorMessage = Constants.ERROR_LOGIN_PASSWORD_INVALID
                    }
                    if (task.exception.toString().equals(Constants.ERROR_LOGIN_ACCOUNT_NOT_EXIST_FIREBASE)) {
                        errorMessage = Constants.ERROR_LOGIN_ACCOUNT_NOT_EXIST
                    }
                    model.sendMessageError(errorMessage)
                }
            }
    }
}

package com.example.tutienda.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginRepository : ILoginRepository {

    private var model: ILoginMVP.model
    private var mAuth: FirebaseAuth? = null

    constructor(model: ILoginMVP.model){
        this.model = model
    }

    override fun login(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    model.loginSuccesfull()
                } else {
                    // model.sendErrorMessage()
                    Log.d("ERROR",task.exception.toString())
                }
            }
    }
}

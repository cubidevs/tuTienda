package com.example.tutienda.login

import com.example.tutienda.register.model.User
import com.example.tutienda.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginRepository(private var model: ILoginMVP.model) : ILoginRepository {

    private var mAuth: FirebaseAuth? = null

    override fun login(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    model.loginSuccesfull()
                    getUserData()

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

    fun getUserData() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val fullName = p0.child("fullName").getValue(String::class.java)
                    val urlPhoto = p0.child("urlPhoto").getValue(String::class.java)
                    val cellPhone = p0.child("cellPhone").getValue(String::class.java)
                    val email = p0.child("email").getValue(String::class.java)
                    val user = User(fullName!!, email!!, cellPhone!!)
                    user.urlPhoto = urlPhoto!!
                    user.uid = uid
                    model.saveUser(user)
                }
            }
        })
    }
}


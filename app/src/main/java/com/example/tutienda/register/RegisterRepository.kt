package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User
import com.example.tutienda.utils.Constants
import com.example.tutienda.utils.Constants.Companion.PICTURE_PATH
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class RegisterRepository : IRegisterRepository {

    private var mAuth: FirebaseAuth? = null
    private var mStorageRef: StorageReference? = null
    private var model: IRegisterMVP.model

    constructor(model: IRegisterMVP.model) {
        this.model = model
    }

    override fun createUser(user: User, password: String, uriPhoto: Uri?) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    model.userCreated(user, uriPhoto)
                } else {
                    if (task.exception.toString().equals(Constants.ERROR_REGISTER_EMAIL_EXIST_FIREBASE)) {
                        model.sendMessageError(Constants.ERROR_REGISTER_EMAIL_EXIST)
                    }
                }
            }
    }

    override fun savePhoto(uriPhoto: Uri?, user: User) {
        if (uriPhoto != null) {
            mStorageRef = FirebaseStorage.getInstance().reference
            val file = user.fullName
            val ref =
                FirebaseStorage.getInstance().getReference("$PICTURE_PATH${mAuth!!.currentUser!!.uid}/$file")

            ref.putFile(uriPhoto)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    ref.downloadUrl.addOnSuccessListener {
                        it.toString()
                        var urlPhoto = it.toString()
                        model.photoSaved(urlPhoto, user)
                    }
                })
                .addOnFailureListener(OnFailureListener {

                })
        } else {
            saveUserInDataBase(Constants.NO_PHOTO, user)
        }
    }

    override fun saveUserInDataBase(urlPhoto: String, user: User) {
        val uid = FirebaseAuth.getInstance().uid ?: Constants.EMPTY
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        user.urlPhoto = urlPhoto
        user.uid = uid
        ref.setValue(user)
            .addOnSuccessListener {
                model.userSavedInDataBase()
            }
    }
}

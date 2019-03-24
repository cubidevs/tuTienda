package com.example.tutienda.register

import android.graphics.Bitmap
import android.net.Uri
import com.example.tutienda.register.model.User

import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import java.io.File
import com.google.firebase.auth.FirebaseUser




class RegisterRepository:IRegisterRepository{

    private var mAuth: FirebaseAuth? = null
    private var mStorageRef: StorageReference? = null
    private lateinit var idUser:String
    private lateinit var nameUser:String
    private lateinit var model:IRegisterMVP.model

    constructor(model:IRegisterMVP.model){
        this.model=model
    }
´
    override fun createUser(user: User) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                     val currentUser = mAuth!!.currentUser
                     idUser=currentUser!!.uid
                     nameUser=user.fullName
                     model.userCreated(user)
                } else {
                   // model.sendErrorMessage()
                    Log.d("ERROR",task.exception.toString())
                }

            })
    }
    override fun savePhoto(urlPhoto: Uri) {
        mStorageRef = FirebaseStorage.getInstance().reference
        val file = nameUser
        val riversRef = FirebaseStorage.getInstance().getReference("/userProfilePicture/$idUser/$file")

        riversRef.putFile(urlPhoto)
            .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                // Get a URL to the uploaded content
                val downloadUrl = taskSnapshot.getDownloadUrl()
            })
            .addOnFailureListener(OnFailureListener {
                // Handle unsuccessful uploads
                // ...
            })
    }

}
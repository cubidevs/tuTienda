package com.example.tutienda.register

import android.net.Uri
import com.example.tutienda.register.model.User
import android.util.Log
import android.widget.Toast
import com.example.tutienda.R
import com.example.tutienda.register.ui.RegisterActivity
import com.example.tutienda.root.TuTienda
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase

class RegisterRepository:IRegisterRepository{

    private lateinit var mAuth:FirebaseAuth
    private var mStorageRef: StorageReference? = null
    private lateinit var model:IRegisterMVP.model

    constructor(model:IRegisterMVP.model){
        this.model=model
    }

    override fun createUser(user: User, uriPhoto: Uri?) {

        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener(RegisterActivity()) {
                    task ->
                    if (task.isComplete) {
                         model.userCreated(user, uriPhoto)
                } else {

                    }
            }
    }

    override fun savePhoto(uriPhoto: Uri?, user: User) {

        if (uriPhoto !=null){
            mStorageRef = FirebaseStorage.getInstance().reference
            val file = user.fullName
            val ref = FirebaseStorage.getInstance().getReference("/userProfilePicture/${mAuth!!.currentUser!!.uid}/$file")

            ref.putFile(uriPhoto)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    ref.downloadUrl.addOnSuccessListener {
                        it.toString()
                        Log.d("RegistroActivity","Ubicacion del archivo: $it")
                        var urlPhoto=it.toString()
                        saveUserInDataBase(urlPhoto,user)
                    }
                })
                .addOnFailureListener(OnFailureListener {
                    // Handle unsuccessful uploads
                    // ...
                })
        }else{
            saveUserInDataBase("NoPhoto",user)
        }
    }

    fun saveUserInDataBase(urlPhoto:String,user:User){
        val uid=FirebaseAuth.getInstance().uid ?:""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        user.urlPhoto=urlPhoto
        user.password=""
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("USUARIO_CREADO",user.toString())
            }
    }
}

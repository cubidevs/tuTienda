package com.example.tutienda.register

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.root.TuTienda
import java.io.ByteArrayOutputStream

class RegisterPresenter() :IRegisterMVP.presenter{



    private lateinit var view:IRegisterMVP.view
    private lateinit var model:IRegisterMVP.model
    private lateinit var uri:Uri
    constructor(view: IRegisterMVP.view) : this() {
        this.view=view
        model=RegisterModel(this)
    }

    override fun registerButtonClicked() {

        var validateCode= ValidateFields().getValideRegister(view.getFullName(),view.getEmail(),view.getCell(),
            view.getPassword(),view.getRepeatPassword(),view.getConditions())

        val uri= getImageUri(view.getContext(),view.getPhoto())

        if (validateCode==ValidateFields().CORRECT_DATA){
            model.sendUser(view.getFullName(),view.getEmail(),view.getCell(),
                view.getPassword(),uri)
        }else{
            ValidateFields().setErrorField(validateCode, view.getView())
        }

    }
    override fun getUriPhoto() {
        uri = getImageUri(TuTienda().getAppContext()!!,view.getPhoto())
    }


    private fun getImageUri(context: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 30, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}
package com.example.tutienda.register

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.utils.Constants
import java.io.ByteArrayOutputStream

class RegisterPresenter() : IRegisterMVP.presenter {

    private lateinit var view: IRegisterMVP.view
    private lateinit var model: IRegisterMVP.model
    private var uri: Uri? = null

    constructor(view: IRegisterMVP.view) : this() {
        this.view = view
        model = RegisterModel(this)
    }

    override fun registerButtonClicked() {
        view.showProgressView()
        var validateCode = ValidateFields().getValideRegister(
            view.getFullName(), view.getEmail(), view.getCellPhone(),
            view.getPassword(), view.getRepeatPassword(), view.getConditions()
        )

        if (view.getPhoto() != null) {
            uri = getImageUri(view.getContext(), view.getPhoto()!!)
        } else {
            uri = null
        }

        if (validateCode == Constants.CORRECT_DATA) {
            model.sendUser(view.getFullName(), view.getEmail(), view.getCellPhone(), view.getPassword(), uri)
        } else {
            ValidateFields().setErrorField(validateCode, view.getView())
            view.hideProgressView()
        }
    }

    override fun getUriPhoto() {
        if (view.getPhoto() != null) {
            uri = getImageUri(view.getContext(), view.getPhoto()!!)
        } else {
            uri = null
        }
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, Constants.QUALITY, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, inImage, Constants.TITLE, null)
        return Uri.parse(path)
    }

    override fun userSavedInDataBase() {
        view.hideProgressView()
        view.showSucces(Constants.USER_CREATED)
    }

    override fun sendMessageError(errorMessage: String) {
        view.hideProgressView()
        view.showError(errorMessage)
    }
}

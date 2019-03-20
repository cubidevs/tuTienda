package com.example.tutienda.register

import android.widget.Toast
import com.example.tutienda.R
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.register.ui.RegisterFragment

class RegisterPresenter() :IRegisterMVP.presenter{


    private lateinit var view:IRegisterMVP.view
    private lateinit var model:IRegisterMVP.model

    constructor(view: IRegisterMVP.view) : this() {
        this.view=view
        model=RegisterModel(this)
    }

    override fun registerButtonClicked() {

        var validateCode= ValidateFields().getValideRegister(view.getFullName(),view.getEmail(),view.getCell(),
            view.getPassword(),view.getRepeatPassword(),view.getConditions())

        if (validateCode==ValidateFields().CORRECT_DATA){
            view.showSucces("Todo Bien")
        }else{
            ValidateFields().setErrorField(validateCode, view.getView())
        }

    }

}
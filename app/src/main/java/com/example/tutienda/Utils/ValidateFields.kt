package com.example.tutienda.Util

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.tutienda.R
import java.util.regex.Pattern

class ValidateFields() {

    val CORRECT_DATA:Int = 100
    private val EMPTY_FUllNAME:Int =101
    private val EMPTY_EMAIL=102
    private val EMPTY_PASSWORD=103
    private val REPEAT_PASSWORD=104
    private val SHORT_FIELD=105
    private val INVALID_EMAIL=106

    private val MINIMUN_FIELD=3

    fun valideEmail(email:String):Boolean{
        var pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun getValideRegister(fullName:String,email:String/*,cellPhone:String,password:String,repeatPassword:String*/): Int {
        if (fullName.isEmpty() && fullName.length > MINIMUN_FIELD){

            return EMPTY_FUllNAME
        }

        if(email.isEmpty()){
            return EMPTY_EMAIL
        }
        if(!valideEmail(email.trim())){
            return INVALID_EMAIL
        }

        return CORRECT_DATA
    }
/*
    fun toast(context: Context,text:String){
        Toast.makeText(context, text,Toast.LENGTH_SHORT).show()
    }*/

    fun getIdError(state:Int):Int{
        var error:Int=0
        when(state){
            EMPTY_FUllNAME -> error=R.string.empty_field_fullname
            EMPTY_EMAIL -> error=R.string.empty_field_email
            INVALID_EMAIL -> error=R.string.invalid_email
        }
        return error
    }

    fun setErrorField(id:Int,view: View){
        var error=getIdError(id)
        var labError=view.context.getString(error)
        lateinit var editText:EditText
        var idView:Int=0
        when(id){
            EMPTY_FUllNAME-> {
                idView=R.id.etFullName
                editText = view.findViewById(idView)
                editText.error=labError
            }
            EMPTY_EMAIL->{
                idView=R.id.etEmail
                editText = view.findViewById(idView)
                editText.error=labError
            }
            INVALID_EMAIL -> {
                idView=R.id.etEmail
                editText = view.findViewById(idView)
                editText.error=labError
            }

        }

    }


}
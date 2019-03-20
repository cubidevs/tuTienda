package com.example.tutienda.Util

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
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
    private val EMPTY_CELL = 107
    private val INCOMPLETE_CELL = 108
    private val SHORT_PASSWORD = 109
    private val NO_ACCEPT_CONDITIONS = 110

    private val MINIMUN_FIELD=3
    private val MINIMUN_CELL_NUMBER= 10
    private val MINIMUN_CHARACTER_PASSWORD=6

    fun valideEmail(email:String):Boolean{
        var pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun getValideRegister(fullName:String,email:String,cellPhone:String,password:String,repeatPassword:String,conditions:Boolean): Int {
        if (fullName.isEmpty()){
            return EMPTY_FUllNAME
        }
        if (fullName.length < MINIMUN_FIELD){
            return SHORT_FIELD
        }
        if  (email.isEmpty()){
            return EMPTY_EMAIL
        }
        if  (!valideEmail(email.trim())){
            return INVALID_EMAIL
        }
        if  (cellPhone.isEmpty()){
            return EMPTY_CELL
        }
        if (cellPhone.length < MINIMUN_CELL_NUMBER ){
            return INCOMPLETE_CELL
        }
        if (password.isEmpty()){
            return EMPTY_PASSWORD
        }
        if (password.length < MINIMUN_CHARACTER_PASSWORD){
            return SHORT_PASSWORD
        }
        if (password != repeatPassword){
            return REPEAT_PASSWORD
        }
        if (!conditions){
            return NO_ACCEPT_CONDITIONS
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
            SHORT_FIELD -> error=R.string.invalid_name
            EMPTY_EMAIL -> error=R.string.empty_field_email
            INVALID_EMAIL -> error=R.string.invalid_email
            EMPTY_CELL -> error=R.string.empty_field_cellphone
            INCOMPLETE_CELL -> error= R.string.incomplete_cell_number
            EMPTY_PASSWORD -> error=R.string.empty_field_password
            SHORT_PASSWORD -> error=R.string.short_field_password
            REPEAT_PASSWORD ->error=R.string.different_field_password
            NO_ACCEPT_CONDITIONS -> error=R.string.need_accept_conditions
        }
        return error
    }

    fun setErrorField(id:Int,view: View){
        var error=getIdError(id)
        var labError=view.context.getString(error)
        lateinit var editText:EditText
        lateinit var checkBox: CheckBox
        var idView:Int=0
        when(id){
            EMPTY_FUllNAME-> {
                idView=R.id.etFullName
                editText = view.findViewById(idView)
                editText.error=labError
            }
            SHORT_FIELD-> {
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
            EMPTY_CELL -> {
                idView=R.id.etCellPhone
                editText = view.findViewById(idView)
                editText.error=labError
            }
            INCOMPLETE_CELL -> {
                idView=R.id.etCellPhone
                editText = view.findViewById(idView)
                editText.error=labError
            }
            EMPTY_PASSWORD -> {
                idView=R.id.etRegisterPassword
                editText = view.findViewById(idView)
                editText.error=labError
            }
            SHORT_PASSWORD -> {
                idView=R.id.etRegisterPassword
                editText = view.findViewById(idView)
                editText.error=labError
            }
            REPEAT_PASSWORD -> {
                idView=R.id.etRegisterRepeatPassword
                editText = view.findViewById(idView)
                editText.error=labError
            }
            NO_ACCEPT_CONDITIONS -> {
                idView=R.id.cbConditions
                checkBox=view.findViewById(idView)
                checkBox.setTextColor(Color.RED)
                checkBox.error=labError
               // editText = view.findViewById(idView)
               // editText.setTextColor(Color.RED)
            }

        }

    }


}
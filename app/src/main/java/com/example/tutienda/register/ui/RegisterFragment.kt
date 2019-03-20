package com.example.tutienda.register.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.tutienda.R
import com.example.tutienda.register.IRegisterMVP
import com.example.tutienda.register.RegisterPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment(),IRegisterMVP.view {



    private lateinit var viewFragment:View
    private lateinit var presenter:IRegisterMVP.presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewFragment=inflater.inflate(R.layout.fragment_register, container, false)

        presenter=RegisterPresenter(this)

        viewFragment.bRegister.setOnClickListener {
            presenter.registerButtonClicked()

        }

        return viewFragment


    }

    override fun getFullName():String = etFullName.text.toString()
    override fun getEmail():String = etEmail.text.toString()
    override fun getCell():String = etCellPhone.text.toString()
    override fun getPassword():String = etRegisterPassword.text.toString()
    override fun getRepeatPassword():String = etRegisterRepeatPassword.text.toString()
    override fun getView(): View =viewFragment
    override fun getConditions(): Boolean = cbConditions.isChecked

    override fun showError(errorMessage: String) {
           /* etFullName.error="Debe digitar algo no se"
            Toast.makeText(activity?.applicationContext,"Debe digitar el $errorMessage",Toast.LENGTH_SHORT).show()*/

    }
    override fun showSucces(succesMessage: String) {
        Toast.makeText(activity?.applicationContext,succesMessage,Toast.LENGTH_SHORT).show()

    }
}

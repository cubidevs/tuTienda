package com.example.tutienda.login.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tutienda.MainActivity
import com.example.tutienda.R
import com.example.tutienda.login.ILoginMVP
import com.example.tutienda.login.LoginPresenter
import com.example.tutienda.register.ui.RegisterActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment(), ILoginMVP.view {

    private lateinit var presenter : ILoginMVP.presenter
    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFragment =  inflater.inflate(R.layout.fragment_login, container, false)
        presenter = LoginPresenter(this)
        viewFragment.tvRegister.setOnClickListener {
            val intent = Intent(activity?.applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewFragment.bLogin.setOnClickListener {
            presenter.loginButtonClicked()
        }

        return viewFragment
    }

    override fun getPassword(): String = etRegisterPassword.text.toString()
    override fun getEmail(): String = etEmail.text.toString()
    override fun getView(): View = viewFragment

    override fun showError(errorMessage: String) {

    }

    override fun navigateToMainActivity() {
        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}

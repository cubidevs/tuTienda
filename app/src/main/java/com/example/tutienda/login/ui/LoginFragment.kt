package com.example.tutienda.login.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.tutienda.main.MainActivity
import com.example.tutienda.R
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.login.ILoginMVP
import com.example.tutienda.login.LoginPresenter
import com.example.tutienda.register.ui.RegisterActivity
import com.example.tutienda.utils.IntentHelper
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.progress_view.*

class LoginFragment : Fragment(), ILoginMVP.view {

    private lateinit var presenter: ILoginMVP.presenter
    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFragment = inflater.inflate(R.layout.fragment_login, container, false)
        presenter = LoginPresenter(this)
        viewFragment.tvRegister.setOnClickListener {
            navigateToRegisterActivity()
        }

        viewFragment.bLogin.setOnClickListener {
            if(ValidateFields().isNetworkAvailable(activity!!.applicationContext)){
                presenter.loginButtonClicked()
            }else{
                Toast.makeText(activity!!.applicationContext,R.string.check_internet_connection,Toast.LENGTH_SHORT).show()
            }
        }

        return viewFragment
    }

    override fun getPassword(): String = etRegisterPassword.text.toString()
    override fun getEmail(): String = etEmail.text.toString()
    override fun getView(): View = viewFragment

    override fun showError(errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun navigateToMainActivity() {
        IntentHelper().goToMainActivity(activity!!.applicationContext,MainActivity::class.java)
        activity?.finish()
    }

    override fun showProgressView() {
        progress_view.visibility = VISIBLE
    }

    override fun hideProgressView() {
        progress_view.visibility = GONE
    }

    override fun showWelcomeMessage() {
        Toast.makeText(activity?.applicationContext, R.string.welcomeMessage,Toast.LENGTH_SHORT).show()
    }

    fun navigateToRegisterActivity() {
        val intent = Intent(activity?.applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }
}

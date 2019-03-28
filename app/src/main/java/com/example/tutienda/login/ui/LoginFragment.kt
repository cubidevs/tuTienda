package com.example.tutienda.login.ui


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tutienda.R
import com.example.tutienda.register.ui.RegisterActivity
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    private lateinit var vw:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vw= inflater.inflate(R.layout.fragment_login, container, false)

        vw.tvRegister.setOnClickListener {
            goToRegister()
        }
        return vw
    }

    fun goToRegister(){
        val intent= Intent(activity!!.applicationContext,RegisterActivity::class.java)
        startActivity(intent)
    }
}



package com.example.tutienda.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.tutienda.R
import com.example.tutienda.main.IMainMVP
import com.example.tutienda.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,IMainMVP.view {

    private lateinit var presenter: IMainMVP.presenter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter=MainPresenter(this)
        presenter.validateUserLog()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        presenter.loadUser()
    }

    override fun outApp() = finish()
    override fun getContext() = this

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_close_session-> {
                presenter.closeSession()
                return true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showUser(fullName: String, urlPhoto: String, email: String) {
        val navHead = findViewById<View>(R.id.nav_view) as NavigationView
        val head = navHead.getHeaderView(0)
        head.tvFullName.text = fullName
        head.tvEmail.text = email

        val options = RequestOptions()
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(this).load(urlPhoto)
            .apply(options)
            .into(head.ivProfilePhoto)
    }
}

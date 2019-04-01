package com.example.tutienda.register.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tutienda.main.ui.MainActivity
import com.example.tutienda.R
import com.example.tutienda.Util.ValidateFields
import com.example.tutienda.register.IRegisterMVP
import com.example.tutienda.register.RegisterPresenter
import com.example.tutienda.utils.Constants
import com.example.tutienda.utils.IntentHelper
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.progress_view.*
import java.io.BufferedInputStream
import java.io.FileNotFoundException
import java.io.InputStream

class RegisterFragment : Fragment(), IRegisterMVP.view {

    private lateinit var viewFragment: View
    private lateinit var presenter: IRegisterMVP.presenter
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFragment = inflater.inflate(com.example.tutienda.R.layout.fragment_register, container, false)
        presenter = RegisterPresenter(this)

        viewFragment.bRegister.setOnClickListener {
            if(ValidateFields().isNetworkAvailable(activity!!.applicationContext)){
                presenter.registerButtonClicked()
            }else{
                Toast.makeText(activity!!.applicationContext,R.string.check_internet_connection,Toast.LENGTH_SHORT).show()
            }
        }

        viewFragment.ivProfilePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            intent.type = Constants.ACTION_PICK
            startActivityForResult(
                Intent.createChooser(intent, Constants.CHOOSE_PICTURE),
                Constants.REQUEST_PICTURE
            )
        }
        return viewFragment
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_PICTURE && resultCode == RESULT_OK) {
            if (data == null) {
                Snackbar.make(view, resources.getText(R.string.error_load_photo), Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val imagen = data.data
                try {
                    val inputStream: InputStream = activity!!.contentResolver.openInputStream(imagen)
                    val bis = BufferedInputStream(inputStream)
                    bitmap = BitmapFactory.decodeStream(bis)
                    ivProfilePhoto.setImageBitmap(bitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun getFullName(): String = etFullName.text.toString()
    override fun getEmail(): String = etEmail.text.toString()
    override fun getCellPhone(): String = etCellPhone.text.toString()
    override fun getPassword(): String = etRegisterPassword.text.toString()
    override fun getRepeatPassword(): String = etRegisterRepeatPassword.text.toString()
    override fun getView(): View = viewFragment
    override fun getConditions(): Boolean = cbConditions.isChecked

    override fun getPhoto(): Bitmap? {
        return if (bitmap != null) {
            bitmap
        } else {
            null
        }
    }
    override fun getContext(): Context = activity!!.applicationContext

    override fun showProgressView() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progress_view.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showSucces(succesMessage: String) {
        Toast.makeText(activity?.applicationContext, succesMessage, Toast.LENGTH_LONG).show()
        navigateToMainActivity()
    }

    fun navigateToMainActivity() {
        IntentHelper().goToMainActivity(activity!!.applicationContext, MainActivity::class.java)
        activity?.finish()
    }
}


package com.example.tutienda.register.ui


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.tutienda.register.IRegisterMVP
import com.example.tutienda.register.RegisterPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.io.BufferedInputStream
import java.io.FileNotFoundException
import java.io.InputStream


class RegisterFragment : Fragment(), IRegisterMVP.view {


    private val SOLICITUD_SELECCIONAR_FOTO = 2
    private lateinit var viewFragment: View
    private lateinit var presenter: IRegisterMVP.presenter
    private lateinit var bitmap:Bitmap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewFragment = inflater.inflate(com.example.tutienda.R.layout.fragment_register, container, false)
        presenter = RegisterPresenter(this)
        viewFragment.bRegister.setOnClickListener {
            presenter.registerButtonClicked()

        }

        viewFragment.ivProfilePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            intent.type = "image/*"
         //   activity!!.startActivityForResult(intent, SOLICITUD_SELECCIONAR_FOTO)
            startActivityForResult(Intent.createChooser(intent,"Seleccionar una foto"),SOLICITUD_SELECCIONAR_FOTO)
        }
        viewFragment.etFullName.setText("Camilo")
        viewFragment.etCellPhone.setText("3209296233")
        viewFragment.etEmail.setText("juancamilocudi@gmail.com")
        viewFragment.etRegisterPassword.setText("1234567890")
        viewFragment.etRegisterRepeatPassword.setText("1234567890")

        return viewFragment


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === SOLICITUD_SELECCIONAR_FOTO && resultCode === RESULT_OK) {

            if (data == null) {
                Toast.makeText(activity!!.applicationContext, "ERROR Cargando Foto", Toast.LENGTH_SHORT).show()
            } else {
                val imagen = data.data

                try {
                    val inputStream: InputStream = activity!!.contentResolver.openInputStream(imagen)
                    val bis = BufferedInputStream(inputStream)
                    bitmap = BitmapFactory.decodeStream(bis)
                    ivProfilePhoto.setImageBitmap(bitmap)
                    // guardarImagen(bitmap);
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

            }
        }
    }

    override fun getFullName(): String = etFullName.text.toString()
    override fun getEmail(): String = etEmail.text.toString()
    override fun getCell(): String = etCellPhone.text.toString()
    override fun getPassword(): String = etRegisterPassword.text.toString()
    override fun getRepeatPassword(): String = etRegisterRepeatPassword.text.toString()
    override fun getView(): View = viewFragment
    override fun getConditions(): Boolean = cbConditions.isChecked
    override fun getPhoto(): Bitmap = bitmap
    override fun getContext(): Context = activity!!.applicationContext
    override fun showError(errorMessage: String) {
        /* etFullName.error="Debe digitar algo no se"
         Toast.makeText(activity?.applicationContext,"Debe digitar el $errorMessage",Toast.LENGTH_SHORT).show()*/

    }

    override fun showSucces(succesMessage: String) {
        Toast.makeText(activity?.applicationContext, succesMessage, Toast.LENGTH_SHORT).show()

    }
}

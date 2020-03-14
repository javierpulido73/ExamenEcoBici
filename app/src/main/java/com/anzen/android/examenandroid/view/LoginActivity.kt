package com.anzen.android.examenandroid.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.presenter.LoginPresenter
import com.anzen.android.examenandroid.utils.FuncionesGenerales.hasPermissionLocation
import kotlinx.android.synthetic.main.activity_login.*
import mx.isoft.fast_delivery.utils.REQUEST_CODE_COARSE_LOCATION
import mx.isoft.fast_delivery.utils.REQUEST_CODE_FINE_LOCATION
import mx.isoft.fast_delivery.utils.TAG_APP
import kotlin.properties.Delegates

/**
 *Clase que construye la vista Login
 */
class LoginActivity : AppCompatActivity() {
    private var presenter: LoginPresenter by Delegates.notNull()

    /**
     * onCreate de la vista Login
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Inicializamos el constructor
        presenter = LoginPresenter(this)
        presenter?.habilitarBotonIniciarSesion()
        iniciarListeners()
        validarPermisoFineLocation()
        validarPermisoCoarseLocation()
    }

    /**
     * validamos que el usuario tenga el permiso ACCESS_FINE_LOCATION
     */
    private fun validarPermisoFineLocation() {
        if (!hasPermissionLocation(this, Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            Log.i(TAG_APP, "No hay permiso ACCESS_FINE_LOCATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE_FINE_LOCATION
                )
            }
        } else {
            Log.i(TAG_APP, "Si hay permiso ACCESS_FINE_LOCATION")
        }
    }

    /**
     * validamos que el usuario tenga el permiso ACCESS_COARSE_LOCATION
     */
    private fun validarPermisoCoarseLocation() {
        if (!hasPermissionLocation(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.i(TAG_APP, "No hay permiso ACCESS_COARSE_LOCATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_CODE_COARSE_LOCATION
                )
            }
        } else {
            Log.i(TAG_APP, "Si hay permiso ACCESS_COARSE_LOCATION")
        }
    }

    /**
     * Función encargada de inicializar los lísteners del actividad
     */
    private fun iniciarListeners() {
        usuarioEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                presenter.habilitarBotonIniciarSesion()
            }
        })
        passwordPv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                presenter.habilitarBotonIniciarSesion()
            }
        })
        loginBtn.setOnClickListener { presenter.botonLoginPresionado() }
    }
}

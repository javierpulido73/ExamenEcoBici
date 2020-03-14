package com.anzen.android.examenandroid.presenter

import android.R
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.anzen.android.examenandroid.view.LoginActivity
import com.anzen.android.examenandroid.view.PrincipalActivity
import kotlinx.android.synthetic.main.activity_login.*
import mx.isoft.fast_delivery.utils.cambiarEstadoBoton

/**
 * Clase que contiene la lógica de la vista Login
 */
class LoginPresenter(private val view: LoginActivity) {


    /**
     * Función encargada de habilitar o deshabilitar el botón de iniciar sesión
     */
    fun habilitarBotonIniciarSesion() {
        val habilitar =
            view.usuarioEt.text.toString().isNotEmpty() && view.passwordPv.text.toString()
                .isNotEmpty()
        cambiarEstadoBoton(view, view.loginBtn, habilitar)
    }

    /**
     * Lógica cuando se presiona el botón login
     */
    fun botonLoginPresionado() {
        if (view.usuarioEt.text.toString().isNotEmpty() && view.passwordPv.text.toString()
                .isNotEmpty()
        ) {
            view.startActivity(Intent(view, PrincipalActivity::class.java))
            view.finish()
        } else {
            AlertDialog.Builder(view)
                .setTitle("Completa la información")
                .setMessage("Es necesario ingresar usuario y contraseña")
                .setPositiveButton(
                    "Aceptar"
                ) { dialog, _ -> dialog.cancel() }
                .setIcon(R.drawable.ic_dialog_alert)
                .show()
        }
    }
}
package mx.isoft.fast_delivery.utils

import android.app.Activity
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.anzen.android.examenandroid.R

/**
 * Función encargada de habilitar o deshabilitar un botón visualmente
 */
fun cambiarEstadoBoton(view: Activity, button: Button, habilitado: Boolean) = try {
    view.runOnUiThread {
        if (habilitado) {
            button.background = ContextCompat.getDrawable(view, R.drawable.background_boton_enable)
            button.setTextColor(ContextCompat.getColor(view, R.color.blanco))
        } else {
            button.background = ContextCompat.getDrawable(view, R.drawable.background_boton_disable)
            button.setTextColor(ContextCompat.getColor(view, R.color.gris_5))
        }
    }
} catch (e: Exception) {
    Log.e(TAG_APP, e.message)
    e.printStackTrace()
}
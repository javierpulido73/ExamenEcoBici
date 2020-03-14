package com.anzen.android.examenandroid.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.presenter.SplashPresenter
import com.anzen.android.examenandroid.utils.FuncionesGenerales
import com.anzen.android.examenandroid.utils.VariablesGlobales
import kotlinx.android.synthetic.main.activity_splash.*
import mx.isoft.fast_delivery.utils.SPLASH_TIME_OUT
import mx.isoft.fast_delivery.utils.TAG_APP

/**
 * Clase que construye la vista Splash
 */
class SplashActivity : AppCompatActivity() {

    var presenter:SplashPresenter?= null

    /**
     *onCreate de la vista
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG_APP, "--onCreate--")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        iniciarListenerGps()
        presenter=SplashPresenter(this)
        versionTv.setText(FuncionesGenerales.getVersion(this))
        //Iniciamos un contador para después redirigir al LoginActivity
        Handler().postDelayed({
            Log.i(TAG_APP, "Condición:" + VariablesGlobales.stationsDtoList.isEmpty())
            if (VariablesGlobales.stationsDtoList.isEmpty()) {
                //loadDefaultCycles()
                presenter!!.loadDefaultCycles()
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    /**
     * Función encargada de iniciar el listener para la geolocalización
     */
    private fun iniciarListenerGps() {
        try {
            //Iniciamos el listener de geolocalización
            FuncionesGenerales.startLocationListener(this)
        } catch (e: java.lang.Exception) {
            Log.e(TAG_APP, e.message)
            e.printStackTrace()
        }
    }

}

package com.anzen.android.examenandroid.presenter;

import android.content.res.Resources;
import android.util.Log;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.model.StationsDto;
import com.anzen.android.examenandroid.utils.FuncionesGenerales;
import com.anzen.android.examenandroid.view.SplashActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

import static com.anzen.android.examenandroid.utils.VariablesGlobales.stationsDtoList;
import static mx.isoft.fast_delivery.utils.ConstantesKt.TAG_APP;

/**
 * Clase que contiene la lógica de la vista Splash
 */
public class SplashPresenter {
    private final SplashActivity view;


    /**
     * @param view vista SplashActivity
     */
    public SplashPresenter(SplashActivity view) {
        this.view = view;
    }

    /**
     * Cargamos el JSON desde el archivo raw
     */
    public void loadDefaultCycles() {
        Log.i(TAG_APP, "--loadDefaultCycles--");
        try {
            Resources res = view.getResources();
            InputStream isFile = res.openRawResource(R.raw.bikes);
            byte[] bytes = new byte[isFile.available()];
            isFile.read(bytes);
            Gson gson = new Gson();
            List<StationsDto> stations = gson.fromJson(new String(bytes), new TypeToken<List<StationsDto>>() {
            }.getType());
            for (StationsDto stationsDto : stations) {
                if (stationsDto.getBikes() != null && stationsDto.getBikes() != 0) {
                    stationsDto.setDistance(FuncionesGenerales.getDistance(stationsDto.getLat(), stationsDto.getLon()));
                    stationsDtoList.add(stationsDto);
                }
            }
            //Ordenamos las bicis por distancia
            FuncionesGenerales.ordenarEstacionesDistancia(true);
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }

    }
}

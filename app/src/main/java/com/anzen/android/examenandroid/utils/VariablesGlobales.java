package com.anzen.android.examenandroid.utils;

import com.anzen.android.examenandroid.model.StationsDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Icom_JP on 2020-03-13.
 * Description: variables estáticas usadas en toda la aplicación
 */
public class VariablesGlobales {

    //Lista de estaciones
    public static List<StationsDto> stationsDtoList =new ArrayList<>();

    //Coordenadas del dispositivo (se actualizan en tiempo real)
    public static double latitude = 0;
    public static double longitude = 0;
}

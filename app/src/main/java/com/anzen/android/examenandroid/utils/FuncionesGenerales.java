package com.anzen.android.examenandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.anzen.android.examenandroid.model.StationsDto;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static com.anzen.android.examenandroid.utils.VariablesGlobales.latitude;
import static com.anzen.android.examenandroid.utils.VariablesGlobales.longitude;
import static mx.isoft.fast_delivery.utils.ConstantesKt.TAG_APP;

/**
 * Created by Icom_JP on 2020-03-13.
 * Description: Clase que contiene funciones estáticas para uso general de la aplicación
 */
public class FuncionesGenerales {

    //Cliente para escuchar la ubicación del usuario
    private static FusedLocationProviderClient mFusedLocationClient;

    /**
     * Valida si hay un permiso en el sistema para la app
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermissionLocation(final Context context, final String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Función encargada de escuchar en tiempo real la ubicación del usuario
     * @param context
     */
    public static void startLocationListener(final Context context) {
        try {
            if (isLocationEnabled(context)) {
                mFusedLocationClient = new FusedLocationProviderClient(context);
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                try {
                                    Location location = task.getResult();
                                    if (location == null) {
                                        requestNewLocationData(context);
                                    } else {
                                        latitude = location.getLatitude();
                                        longitude = location.getLongitude();
                                        Log.i(TAG_APP, "------2> getLongitude:" + location.getLongitude() + " getLatitude:" + location.getLatitude());
                                    }
                                } catch (Exception e) {
                                    Log.e(TAG_APP, e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Forzamos a actualizar la localización
     * @param context
     */
    private static void requestNewLocationData(final Context context) {
        try {
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(0);
            mLocationRequest.setFastestInterval(0);
            mLocationRequest.setNumUpdates(1);
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
            mFusedLocationClient.requestLocationUpdates(
                    mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            Location mLastLocation = locationResult.getLastLocation();
                            latitude = mLastLocation.getLatitude();
                            longitude = mLastLocation.getLongitude();
                            Log.i(TAG_APP, "------1> getLongitude:" + mLastLocation.getLongitude() + " getLatitude:" + mLastLocation.getLatitude());
                        }
                    },
                    Looper.myLooper()
            );
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * validamos que el permiso de gps se encuentre habilitado
     * @param context
     * @return
     */
    private static boolean isLocationEnabled(final Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
            );
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ordenamos la lista de estaciones con base en la distancia hacia el usuario
     * @param ordenAscendente
     */
    public static void ordenarEstacionesDistancia(final boolean ordenAscendente) {
        Log.i(TAG_APP, "---ordenarEstacionesDistancia--");
        Collections.sort(VariablesGlobales.stationsDtoList, new Comparator<StationsDto>() {
            @Override
            public int compare(StationsDto resultado1, StationsDto resultado2) {
                int expre = resultado1.getDistance().compareTo(resultado2.getDistance());
                if (expre > 0) {
                    return ordenAscendente ? 1 : -1;
                } else if (expre < 0) {
                    return ordenAscendente ? -1 : 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * @param lat2
     * @param lon2
     * @return
     */
    public static double getDistance(double lat2, double lon2) {
        double theta = longitude - lon2;
        double dist = Math.sin(deg2rad(latitude))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(latitude))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return dist;
    }

    /**
     * @param deg
     * @return
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * @param rad
     * @return
     */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /**
     * Extrae la versión de la aplicación
     *
     * @return
     */
    public static String getVersion(final Context context) {
        String ver = "1.0";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ver = pInfo.versionName;
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }
        return "versión " + ver;
    }
}

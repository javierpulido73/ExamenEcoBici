package com.anzen.android.examenandroid.presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.model.StationsDto;
import com.anzen.android.examenandroid.utils.VariablesGlobales;
import com.anzen.android.examenandroid.view.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static mx.isoft.fast_delivery.utils.ConstantesKt.TAG_APP;
import static mx.isoft.fast_delivery.utils.ConstantesKt.TAM_ICON_CICLE_INT;

/**
 * Clase que contiene la lógica de la vista Mapa
 */
public class MapPresenter {

    private static MapFragment view;

    /**
     * @param view
     */
    public MapPresenter(MapFragment view) {
        this.view = view;
    }

    /**
     * Agregamos las estaciones de EcoBicis
     */
    public void addStations() {
        LatLng myPosition = new LatLng(VariablesGlobales.latitude, VariablesGlobales.longitude);
        view.mMap.addMarker(new MarkerOptions().position(myPosition).title("Tú ubicación...").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        for (StationsDto stationsDto : VariablesGlobales.stationsDtoList) {
            LatLng stationPosition = new LatLng(stationsDto.getLat(), stationsDto.getLon());
            view.mMap.addMarker(new MarkerOptions().position(stationPosition).title(stationsDto.getName()).icon(getIcon()));
        }
        //Nos dirigimos al punto del usuario
        view.mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
    }


    /**
     * Función encargada de extraer el ícono
     *
     * @return
     */
    public BitmapDescriptor getIcon() {
        try {
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), R.drawable.ic_ico_station);
            Bitmap bmIcon = drawableToBitmap(drawable);
            if (bmIcon == null)
                Log.i(TAG_APP, "ES NULLL");
            return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bmIcon,
                    TAM_ICON_CICLE_INT,
                    TAM_ICON_CICLE_INT,
                    false));
        } catch (Exception e) {
            Log.e(TAG_APP, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}

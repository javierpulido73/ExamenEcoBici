package com.anzen.android.examenandroid.presenter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.view.EstacionesFragment;
import com.anzen.android.examenandroid.view.MapFragment;
import com.anzen.android.examenandroid.view.PrincipalActivity;

import static mx.isoft.fast_delivery.utils.ConstantesKt.POSICION_STATIONS;
import static mx.isoft.fast_delivery.utils.ConstantesKt.POSICION_MAP;
import static mx.isoft.fast_delivery.utils.ConstantesKt.TAG_APP;

/**
 * Clase que contiene la lógica de la vista Principal
 */
public class PrincipalPresenter {
    private final PrincipalActivity view;

    private MapFragment mapFragment;
    private EstacionesFragment estacionesFragment;

    /**
     * Constructor del presentador de la vista Principal
     * @param view recibimos una referencia de la vista principal
     */
    public PrincipalPresenter(PrincipalActivity view) {
        this.view = view;
        mapFragment=new MapFragment();
        estacionesFragment=new EstacionesFragment();
    }

    /**
     * Función encargada de cambiarnos de Fragment
     * @param fragmentClass recibe una referencia del fragment
     */
    private void cambiarFragment(final Fragment fragmentClass) {
        FragmentTransaction fragmentTransaction = view.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, fragmentClass);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Función encargada de redirigirnos a una posición de Fragment
     * @param posicion recibe la posición de fragment a cambiar
     */
    public void cambiarPosicionFragment(final int posicion) {
        Log.i(TAG_APP, "cambiarPosicionFragment:" + posicion);
        switch (posicion) {
            case POSICION_MAP:
                cambiarFragment(mapFragment);
                break;
            case POSICION_STATIONS:
                cambiarFragment(estacionesFragment);
                break;
        }
    }

    /**
     * Función encargada de actualizar el estado de los botones del menú
     * @param itemId
     */
    public void actualizarEstadoDelMenu(int itemId) {
        Menu menu = view.menuBnv.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == itemId);
            //Se hace este ajuste para que el texto no aumente de tamaño al ser seleccionado
            if (item.getItemId() == itemId) {
                SpannableString spanString = new SpannableString(item.getTitle().toString());
                int end = spanString.length();
                spanString.setSpan(new RelativeSizeSpan(0.9f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                item.setTitle(spanString);
            }
        }

    }
}

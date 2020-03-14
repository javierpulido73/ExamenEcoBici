package com.anzen.android.examenandroid.presenter;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.model.StationsDto;
import com.anzen.android.examenandroid.utils.FuncionesGenerales;
import com.anzen.android.examenandroid.utils.VariablesGlobales;
import com.anzen.android.examenandroid.view.EstacionesFragment;

import java.util.Collections;
import java.util.Comparator;

import static mx.isoft.fast_delivery.utils.ConstantesKt.TAG_APP;

/**
 * Created by Icom_JP on 2020-03-13.
 * Description: Clase que contiene la lógica de la vista Estaciones
 */
public class EstacionesPresenter {
    private final EstacionesFragment view;

    /**
     * @param view vista de EstacionesFragment
     */
    public EstacionesPresenter(EstacionesFragment view) {
        this.view = view;
    }

    /**
     * Setea los valores al spinner
     */
    public void setItemsToSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, view.getContext().getResources().getStringArray(R.array.string_array_filtros));
        view.spinnerFiltro.setAdapter(adapter); // this will set list of values to spinner
        view.spinnerFiltro.setSelection(0, false);
    }

    /**
     * Función encargada de ordenar la lista por distancia
     *
     * @param ordenAscendente
     */
    public void ordenarBicisDistancia(final boolean ordenAscendente) {
        Log.i(TAG_APP, "---ordenarEstacionesDistancia--");
        FuncionesGenerales.ordenarEstacionesDistancia(ordenAscendente);
        //Actualizamos la vista
        view.estacionAdapter.notifyDataSetChanged();
    }

    /**
     * Función encargada de ordenar la lista por bicisDisponibles
     *
     * @param ordenAscendente
     */
    public void ordenarBicisDisponibles(final boolean ordenAscendente) {
        Log.i(TAG_APP, "---ordenarBicisDisponibles--");
        Collections.sort(VariablesGlobales.stationsDtoList, new Comparator<StationsDto>() {
            @Override
            public int compare(StationsDto resultado1, StationsDto resultado2) {
                int expre = resultado1.getBikes().compareTo(resultado2.getBikes());
                if (expre > 0) {
                    return ordenAscendente ? 1 : -1;
                } else if (expre < 0) {
                    return ordenAscendente ? -1 : 1;
                } else {
                    return 0;
                }
            }
        });
        //Actualizamos la vista
        view.estacionAdapter.notifyDataSetChanged();
    }

    /**
     * Función encargada de ordenar la lista por espacios
     *
     * @param ordenAscendente
     */
    public void ordenarEspaciosDisponibles(final boolean ordenAscendente) {
        Log.i(TAG_APP, "---ordenarBicisDisponibles--");
        Collections.sort(VariablesGlobales.stationsDtoList, new Comparator<StationsDto>() {
            @Override
            public int compare(StationsDto resultado1, StationsDto resultado2) {
                int expre = resultado1.getSlots().compareTo(resultado2.getSlots());
                if (expre > 0) {
                    return ordenAscendente ? 1 : -1;
                } else if (expre < 0) {
                    return ordenAscendente ? -1 : 1;
                } else {
                    return 0;
                }
            }
        });
        //Actualizamos la vista
        view.estacionAdapter.notifyDataSetChanged();
    }
}

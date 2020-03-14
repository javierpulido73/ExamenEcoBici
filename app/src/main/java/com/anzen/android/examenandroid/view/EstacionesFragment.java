package com.anzen.android.examenandroid.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.presenter.EstacionesPresenter;
import com.anzen.android.examenandroid.utils.FuncionesGenerales;
import com.anzen.android.examenandroid.view.adapter.EstacionAdapter;

/**
 * Clase encargada de construir el fragment de Estaciones
 */
public class EstacionesFragment extends Fragment {

    private final EstacionesPresenter presenter;
    public EstacionAdapter estacionAdapter;

    public Spinner spinnerFiltro;

    /**
     * Constructor para el fragment
     */
    public EstacionesFragment() {
        presenter = new EstacionesPresenter(this);
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estaciones, null);
        spinnerFiltro = view.findViewById(R.id.spinnerFiltro);
        presenter.setItemsToSpinner();
        ListView resultadosLv = view.findViewById(R.id.lv_resultados);
        estacionAdapter = new EstacionAdapter(this);
        resultadosLv.setAdapter(estacionAdapter);
        //Ordenamos la lista por distancia
        FuncionesGenerales.ordenarEstacionesDistancia(true);
        iniciarListeners();
        return view;
    }

    /**
     * función encargada de inicializar los lísteners del fragment
     */
    private void iniciarListeners() {
        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        presenter.ordenarBicisDistancia(true);
                        break;
                    case 1:
                        presenter.ordenarBicisDisponibles(false);
                        break;
                    case 2:
                        presenter.ordenarEspaciosDisponibles(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}

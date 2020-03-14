package com.anzen.android.examenandroid.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.presenter.PrincipalPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static mx.isoft.fast_delivery.utils.ConstantesKt.INTENT_REDIRECIONAR_FRAGMENT;
import static mx.isoft.fast_delivery.utils.ConstantesKt.POSICION_STATIONS;
import static mx.isoft.fast_delivery.utils.ConstantesKt.POSICION_MAP;

/**
 * Clase que construye la vista Principal
 */
public class PrincipalActivity extends AppCompatActivity {
    private PrincipalPresenter presenter;
    public BottomNavigationView menuBnv;

    /**
     *onCreate de la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        menuBnv = findViewById(R.id.bnv_menu);
        presenter = new PrincipalPresenter(this);
        int direccionarFragment = getIntent().getIntExtra(INTENT_REDIRECIONAR_FRAGMENT, 0);
        //Colocamos el Fragment
        presenter.cambiarPosicionFragment(direccionarFragment);
        //Iniciamos los listeners
        iniciarListeners();
    }

    /**
     *Inicializamos los listeners del componente menú
     */
    private void iniciarListeners() {
        menuBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_prueba:
                        presenter.cambiarPosicionFragment(POSICION_MAP);
                        break;
                    case R.id.item_ssh:
                        presenter.cambiarPosicionFragment(POSICION_STATIONS);
                        break;
                }
                //Actualizamos el estado del menú
                presenter.actualizarEstadoDelMenu(menuItem.getItemId());
                return true;

            }
        });
    }
}

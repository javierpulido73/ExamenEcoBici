package com.anzen.android.examenandroid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anzen.android.examenandroid.R;
import com.anzen.android.examenandroid.view.EstacionesFragment;

import java.util.Objects;

import static com.anzen.android.examenandroid.utils.VariablesGlobales.stationsDtoList;

/**
 * Created by Icom_JP on 2020-03-13.
 * Description: Adaptador para la lista de estaciones
 */
public class EstacionAdapter extends BaseAdapter {

    private final EstacionesFragment view;

    /**
     * Constructor del adaptador
     *
     * @param view recibimos una referencia del fragment
     */
    public EstacionAdapter(final EstacionesFragment view) {
        this.view = view;
    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        return stationsDtoList.size();
    }

    /**
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Construimos los items del adaptador
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(view.getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = Objects.requireNonNull(inflater).inflate(R.layout.item_estacion, parent, false);
            ItemHolder itemHolder = new ItemHolder();
            itemHolder.disponiblesTv = rowView.findViewById(R.id.tvDisponibles);
            itemHolder.espaciosTv = rowView.findViewById(R.id.tvEspacios);
            itemHolder.nombreTv = rowView.findViewById(R.id.nombreTv);
            itemHolder.direccionTv = rowView.findViewById(R.id.direccionTv);
            rowView.setTag(itemHolder);
        }
        final ItemHolder itemHolder = (ItemHolder) rowView.getTag();
        itemHolder.disponiblesTv.setText(String.valueOf(stationsDtoList.get(position).getBikes()));
        itemHolder.espaciosTv.setText(String.valueOf(stationsDtoList.get(position).getSlots()));
        itemHolder.nombreTv.setText(stationsDtoList.get(position).getName());
        itemHolder.direccionTv.setText(stationsDtoList.get(position).getAddress());
        return rowView;
    }

    /**
     * Holder para los componentes del item
     */
    private static class ItemHolder {
        TextView disponiblesTv;
        TextView espaciosTv;
        TextView nombreTv;
        TextView direccionTv;
    }

}

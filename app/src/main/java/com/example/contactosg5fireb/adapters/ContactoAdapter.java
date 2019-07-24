package com.example.contactosg5fireb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactosg5fireb.R;
import com.example.contactosg5fireb.models.ContactoModel;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {
    private final Context context;
    private ContactoModel model;
    private ArrayList<ContactoModel> modelArrayList;

    public ContactoAdapter(Context context, ArrayList<ContactoModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vistaItem = view;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaItem = inflater.inflate(R.layout.item_list_contacto, viewGroup, false);
        }

        TextView tv_item_list_contacto_nombres, tv_item_list_contacto_celular;
        tv_item_list_contacto_nombres = vistaItem.findViewById(R.id.tv_item_list_contacto_nombres);
        tv_item_list_contacto_celular = vistaItem.findViewById(R.id.tv_item_list_contacto_celular);

        model = (ContactoModel)getItem(i);
        String nombres_item = model.get_nombre() + " " + model.get_apellido();
        String celular_item = model.get_numeroCelular();

        tv_item_list_contacto_nombres.setText(nombres_item);
        tv_item_list_contacto_celular.setText(celular_item);

        return vistaItem;
    }
}

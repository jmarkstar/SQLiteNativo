package com.jmarkstar.sesion4.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jmarkstar.sesion4.R;
import com.jmarkstar.sesion4.activities.ClienteAgregarActivity;
import com.jmarkstar.sesion4.model.ClienteModel;
import java.util.List;

/**
 * Created by jmarkstar on 13/05/2017.
 */
public class ClienteAdapter extends ArrayAdapter<ClienteModel> {

    public ClienteAdapter(@NonNull Context context, @NonNull List<ClienteModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClienteModel cliente = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cliente_lista_item, parent, false);
        }

        TextView tvEmpresa = (TextView)convertView.findViewById(R.id.tv_empresa_cliente);
        TextView tvTelefono = (TextView)convertView.findViewById(R.id.tv_telefono);
        TextView tvNombreCompleto = (TextView)convertView.findViewById(R.id.tv_nombre_completo);
        ImageView ivEdit = (ImageView)convertView.findViewById(R.id.iv_edit);
        ivEdit.setTag(position);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                Log.v("adapter", "position = "+position);
                ClienteModel cliente = getItem(position);
                Intent intent = new Intent(getContext(), ClienteAgregarActivity.class);
                intent.putExtra(ClienteAgregarActivity.CLIENTE, cliente);
                getContext().startActivity(intent);
            }
        });

        //Para obtener el nombre de la empresa que el usuario ha selecionado antes, tenemos que buscarlo en el array xml.
        String [] empresasArray = getContext().getResources().getStringArray(R.array.empresas_clientes);
        tvEmpresa.setText(empresasArray[cliente.getIdEmpresaCliente()]);

        tvTelefono.setText(cliente.getTelefono());
        tvNombreCompleto.setText(cliente.getNombreCompleto());

        return convertView;
    }
}

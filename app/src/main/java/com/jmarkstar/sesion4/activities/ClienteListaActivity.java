package com.jmarkstar.sesion4.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmarkstar.sesion4.R;
import com.jmarkstar.sesion4.adapters.ClienteAdapter;
import com.jmarkstar.sesion4.database.dao.ClienteDao;
import com.jmarkstar.sesion4.model.ClienteModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClienteListaActivity extends AppCompatActivity
                                    implements AdapterView.OnItemClickListener{

    @BindView(R.id.lv_lista_clientes) ListView mLvClientes;
    @BindView(R.id.tv_mensaje_vacio) TextView mTvListaVacia;

    private ClienteDao mClienteDao;
    private ClienteAdapter mClienteAdapter;
    private List<ClienteModel> mClientes;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_lista);
        ButterKnife.bind(this);
        mClienteDao = new ClienteDao(this);

        mLvClientes.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        mClientes = mClienteDao.getClientes();
        if(mClientes.size() > 0){
            mLvClientes.setVisibility(View.VISIBLE);
            mTvListaVacia.setVisibility(View.GONE);
            mClienteAdapter = new ClienteAdapter(this, mClientes);
            mLvClientes.setAdapter(mClienteAdapter);
        }else{
            mLvClientes.setVisibility(View.GONE);
            mTvListaVacia.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_agregar) public void onAgregar(){
        Intent intent = new Intent(this, ClienteAgregarActivity.class);
        startActivity(intent);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final ClienteModel cliente = mClientes.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(ClienteListaActivity.this);
        alert.setTitle(R.string.app_name);
        alert.setMessage(R.string.lista_dialog_eliminar_msj);
        alert.setPositiveButton(R.string.dialog_aceptar, new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {
                int success = mClienteDao.eliminarCliente(cliente.getId());
                if(success != 0){
                    mostrarMensaje(getString(R.string.lista_eliminar_correcto));
                    mClientes.remove(position);
                    mClienteAdapter.notifyDataSetChanged();
                }else{
                    mostrarMensaje(getString(R.string.lista_eliminar_error));
                }
                dialog.dismiss();
            }
        });
        alert.setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    /** Este metodo mostrará en mensaje al usuario.
     *  @param mensaje texto a mostrar.
     * */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

package com.jmarkstar.sesion4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jmarkstar.sesion4.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClienteListaActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_lista);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_agregar) public void onAgregar(){
        Intent intent = new Intent(this, ClienteAgregarActivity.class);
        startActivity(intent);
    }
}

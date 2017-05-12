package com.jmarkstar.sesion4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jmarkstar.sesion4.R;
import butterknife.ButterKnife;

public class ClienteAgregarActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_agregar);
        ButterKnife.bind(this);
    }
}

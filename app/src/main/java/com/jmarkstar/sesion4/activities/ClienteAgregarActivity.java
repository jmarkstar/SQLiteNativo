package com.jmarkstar.sesion4.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.jmarkstar.sesion4.R;
import com.jmarkstar.sesion4.database.dao.ClienteDao;
import com.jmarkstar.sesion4.model.ClienteModel;
import com.jmarkstar.sesion4.util.StringUtils;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Este activity nos permitirá crear un nuevo cliente en la base de datos SQLite.
 * @author jmarkstar
 * */
public class ClienteAgregarActivity extends AppCompatActivity {

    public static final String CLIENTE = "cliente_editar";
    private static final String TAG = "ClienteAgregarActivity";

    @BindView(R.id.et_nombre_completo) EditText mEtNombreCompleto;
    @BindView(R.id.et_email) EditText mEtEmail;
    @BindView(R.id.et_phone) EditText mEtPhone;
    @BindView(R.id.et_address) EditText mEtAddress;
    @BindView(R.id.sp_empresas_clientes) Spinner mSpEmpresasClientes;
    @BindView(R.id.tv_cumpleanio) TextView mTvCumpleanio;
    @BindView(R.id.rg_estado_civil) RadioGroup mRgEstadoCivil;
    @BindView(R.id.cb_tiene_hijos) CheckBox mCbTieneHijos;

    private ClienteDao mClienteDao;

    /* Esta variable almacenará el estado civil que seleccionaremos en la pantalla.
     * La variable iniciará en NULL, y los valores que selecionaremos tendrán los posibles valores {1,2,3,4 }
     * */
    private Integer mEstadoCivilSeleccionado = null;

    private Integer mEmpresaClienteSeleccionado = 0;

    private int anio, mes, dia;
    private String mFechaSelecionada = null;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_agregar);

        //Iniciando la libreria ButterKnife en el Activity.
        ButterKnife.bind(this);

        //agregando titulo a la pantalla
        setTitle(getString(R.string.registrar_titutlo));

        //ClienteModel clienteActualizar = getIntent().getParcelableExtra(CLIENTE);
        //Log.v(TAG, "clienteActualizar - "+clienteActualizar.toString());

        //Iniciando nuestro DAO.
        mClienteDao = new ClienteDao(this);

        //Cargando el Spinner
        String [] empresaClientesArray = getResources().getStringArray(R.array.empresas_clientes);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, empresaClientesArray);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpEmpresasClientes.setAdapter(adapterSpinner);

        mSpEmpresasClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mEmpresaClienteSeleccionado = position;
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* Este listener nos permite ocultar el keyboard del dispositivo cuando el usuario
        // hace click en el spinner.
        * */
        mSpEmpresasClientes.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {

                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                return false;
            }
        });

        /* De esta forma capturamos el checkbox seleccionado por el usuario. */
        mRgEstadoCivil.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                /* El parametro 'checkedId' es el id del CheckBox que el usuario ha selecionado,
                 * Para poder saber que opcion ha selecionado tenemos que verificarlo uno por uno
                 * y asignarle el valor a la variable 'mEstadoCivilSeleccionado' segun la condición.
                 * */

                if(checkedId == R.id.rb_soltero){
                    mEstadoCivilSeleccionado = 1;
                }else if(checkedId == R.id.rb_casado){
                    mEstadoCivilSeleccionado = 2;
                }else if(checkedId == R.id.rb_viudo){
                    mEstadoCivilSeleccionado = 3;
                }else if(checkedId == R.id.rb_divorsiado){
                    mEstadoCivilSeleccionado = 4;
                }
            }
        });

        //Iniciando valores de anio, mes y dia con la fecha actual.
        Calendar calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @OnClick(R.id.btn_registrar) public void onRegistrarNuevoCliente(){
        if(validarCampos()){
            registrarCliente();
        }
    }

    @OnClick(R.id.rl_cumpleanio) public void onAbrirDatePicker(){
        /* Creando el DatePicker con el anio, mes y dia que queremos que comienze cuando se muestra.
         * la variable 'miDateListener' es un CallBack o Listener, lo que basicamente es permitir recibir los valores que
         * el DatePicker retorna, porque para nosotros Datepicker es una cajanegra, la unica forma de como obtejer su valor
         * es por medio de este 'OnDateSetListener'.
         * */
        DatePickerDialog mDatePicker = new DatePickerDialog(this, miDateListener, anio, mes, dia);
        mDatePicker.show();
    }

    private DatePickerDialog.OnDateSetListener miDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //se lke suma 1 porque su valor comienza en 0
            mFechaSelecionada = dayOfMonth+"/"+(month+1)+"/"+year;
            mTvCumpleanio.setText(mFechaSelecionada);
            anio = year;
            mes = month;
            dia = dayOfMonth;
        }
    };

    private void registrarCliente(){
        String nombreCompleto = mEtNombreCompleto.getText().toString();
        String email = mEtEmail.getText().toString();
        String phone = mEtPhone.getText().toString();
        String address = mEtAddress.getText().toString();

        ClienteModel cliente = new ClienteModel();
        cliente.setNombreCompleto(nombreCompleto);
        cliente.setEmail(email);
        cliente.setTelefono(phone);
        cliente.setDireccion(address);
        cliente.setFechNacimiento(mFechaSelecionada);
        cliente.setIdEmpresaCliente(mEmpresaClienteSeleccionado);
        cliente.setIdEstadoCivil(mEstadoCivilSeleccionado);
        cliente.setTieneHijos(mCbTieneHijos.isChecked());//guarda el valor de la pregunta si fue checked o no.

        Log.v(TAG, cliente.toString());
        long status = mClienteDao.insertCliente(cliente);
        if(status>0){
            mostrarMensaje(getString(R.string.registrar_msj_correcto));
            finish();//este metodo cierra la pantalla o mata el activity.
        }else{
            mostrarMensaje(getString(R.string.registro_msj_error));
        }
    }

    /** Este metodo valida todos los campos del formulario.
     * @return true o false.
     * */
    private boolean validarCampos(){
        String nombreCompleto = mEtNombreCompleto.getText().toString();
        String email = mEtEmail.getText().toString();
        String phone = mEtPhone.getText().toString();
        String address = mEtAddress.getText().toString();

        if(nombreCompleto.isEmpty()){
            mostrarMensaje(getString(R.string.registrar_validacion_nombre_vacio));
            return false;
        }

        if(email.isEmpty()){
            mostrarMensaje(getString(R.string.registrar_validacion_email_vacio));
            return false;
        }else{
            if(!StringUtils.validarFormatoEmail(email)){
                mostrarMensaje(getString(R.string.registrar_validacion_email_incorrecto));
                return false;
            }
        }

        if(phone.isEmpty()){
            mostrarMensaje(getString(R.string.registrar_validacion_telefono_vacio));
            return false;
        }else{
            if(phone.length() < 7){
                mostrarMensaje(getString(R.string.registrar_validacion_telefono_incorrecto));
                return false;
            }
        }

        if(address.isEmpty()){
            mostrarMensaje(getString(R.string.registrar_validacion_direccion_vacio));
            return false;
        }

        if(mEmpresaClienteSeleccionado==0){
            mostrarMensaje(getString(R.string.register_validacion_selecione_empresa));
            return false;
        }

        if(mFechaSelecionada==null){
            mostrarMensaje(getString(R.string.registrar_validacion_cumpleanos_vacio));
            return false;
        }

        if(mEstadoCivilSeleccionado == null){
            mostrarMensaje(getString(R.string.registrar_validacion_estado_civil_no_selecionado));
            return false;
        }

        return true;
    }

    /** Este metodo mostrará en mensaje al usuario.
     *  @param mensaje texto a mostrar.
     * */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

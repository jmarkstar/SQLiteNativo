package com.jmarkstar.sesion4.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.jmarkstar.sesion4.database.Sesion4DatabaseHelper;
import com.jmarkstar.sesion4.model.ClienteModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 12/05/2017.
 */
public class ClienteDao {

    private static final String TAG = "ClienteDao";

    private final String ALL_COLUMNS [] = {
            ClienteModel.ID_FIELD,
            ClienteModel.NOMBRE_FIELD,
            ClienteModel.EMAIL_FIELD,
            ClienteModel.TELEFONO_FIELD,
            ClienteModel.DIRECCION_FIELD,
            ClienteModel.ID_EMPRESA_FIELD,
            ClienteModel.FECHA_NAC_FIELD,
            ClienteModel.ID_ESTADO_CIVIL_FIELD,
            ClienteModel.TIENE_HIJOS_FIELD};

    private SQLiteDatabase mSQLiteDatabase;

    public ClienteDao(Context context){
        mSQLiteDatabase = new Sesion4DatabaseHelper(context).getWritableDatabase();
    }

    public List<ClienteModel> getClientes(){
        Cursor mCursor = mSQLiteDatabase.query(true, ClienteModel.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);
        return convertCursorToList(mCursor);
    }

    public ClienteModel getClienteById(Integer idCliente){
        return null;
    }

    public long insertCliente(ClienteModel clienteModel){

        /*try{
            //escribirmos nuestra sentencia sql.
        }catch(SQLException ex){
            Log.v(TAG, "ex - "+ex);
        }*/

        /*
        mSQLiteDatabase.beginTransaction();
        //podemos todos los sql que queremos ejecutar.
        mSQLiteDatabase.endTransaction();
        */

        long status = mSQLiteDatabase.insert(ClienteModel.TABLE_NAME, null, convertClienteToContentValues(clienteModel));
        //En metodo 'insert' retorna un long, podría ser el ID de la fila o -1 si ocurre un error.
        Log.v(TAG,"status = "+status);
        return status;
    }

    public int updateCliente(ClienteModel clienteModel){
        String strFilter = ClienteModel.ID_FIELD+"="+clienteModel.getId();
        return mSQLiteDatabase.update(ClienteModel.TABLE_NAME, convertClienteToContentValues(clienteModel), strFilter, null);
    }

    public int eliminarCliente(Integer idCliente){
        String strWhere = ClienteModel.ID_FIELD+"=?";
        String [] whereArgs = new String [] { String.valueOf(idCliente) };
        return mSQLiteDatabase.delete(ClienteModel.TABLE_NAME, strWhere, whereArgs);
    }

    private ContentValues convertClienteToContentValues(ClienteModel cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClienteModel.NOMBRE_FIELD, cliente.getNombreCompleto());
        contentValues.put(ClienteModel.EMAIL_FIELD, cliente.getEmail());
        contentValues.put(ClienteModel.TELEFONO_FIELD, cliente.getTelefono());
        contentValues.put(ClienteModel.DIRECCION_FIELD, cliente.getDireccion());
        contentValues.put(ClienteModel.ID_EMPRESA_FIELD, cliente.getIdEmpresaCliente());
        contentValues.put(ClienteModel.FECHA_NAC_FIELD, cliente.getFechNacimiento());
        contentValues.put(ClienteModel.ID_ESTADO_CIVIL_FIELD, cliente.getIdEstadoCivil());

        //debido a que SQLite no puede guardar booleanos, estamos convirtiendo el Boolean a Integer
        //para poder guardar.
        Integer tieneHijosInteger = cliente.getTieneHijos()?1:0;
        contentValues.put(ClienteModel.TIENE_HIJOS_FIELD, tieneHijosInteger);
        return contentValues;
    }

    private List<ClienteModel> convertCursorToList(Cursor mCursor){
        List<ClienteModel> arrClientes = new ArrayList<>();
        if (mCursor.moveToFirst()) {
            do {
                ClienteModel clienteItem = new ClienteModel();
                clienteItem.setId(mCursor.getInt(mCursor.getColumnIndex(ClienteModel.ID_FIELD)));
                clienteItem.setNombreCompleto(mCursor.getString(mCursor.getColumnIndex(ClienteModel.NOMBRE_FIELD)));
                clienteItem.setEmail(mCursor.getString(mCursor.getColumnIndex(ClienteModel.EMAIL_FIELD)));
                clienteItem.setTelefono(mCursor.getString(mCursor.getColumnIndex(ClienteModel.TELEFONO_FIELD)));
                clienteItem.setDireccion(mCursor.getString(mCursor.getColumnIndex(ClienteModel.DIRECCION_FIELD)));
                clienteItem.setIdEmpresaCliente(mCursor.getInt(mCursor.getColumnIndex(ClienteModel.ID_EMPRESA_FIELD)));
                clienteItem.setFechNacimiento(mCursor.getString(mCursor.getColumnIndex(ClienteModel.FECHA_NAC_FIELD)));
                clienteItem.setIdEstadoCivil(mCursor.getInt(mCursor.getColumnIndex(ClienteModel.ID_ESTADO_CIVIL_FIELD)));

                //recuperando Integer y convirtiendolo a Booleano.
                Boolean tieneHijos = mCursor.getInt(mCursor.getColumnIndex(ClienteModel.TIENE_HIJOS_FIELD)) == 1;
                clienteItem.setTieneHijos(tieneHijos);

                arrClientes.add(clienteItem);
            }while (mCursor.moveToNext());
        }
        return arrClientes;
    }

}

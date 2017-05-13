package com.jmarkstar.sesion4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jmarkstar.sesion4.model.ClienteModel;

/** Esta clase que extiende de la clase {SQLiteOpenHelper} es el encargado de crear y acceder
 * a la base de datos SQLite.
 *
 * Created by jmarkstar on 11/05/2017.
 */
public class Sesion4DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb.db";
    private static final Integer DB_VERSION = 1;

    private final StringBuffer SQL_CREATE_TABLE_CLIENTE = new StringBuffer()
            .append("CREATE TABLE IF NOT EXISTS "+ ClienteModel.TABLE_NAME+" ( ")
            .append(ClienteModel.ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(ClienteModel.NOMBRE_FIELD+" TEXT, ")
            .append(ClienteModel.EMAIL_FIELD+" TEXT, ")
            .append(ClienteModel.TELEFONO_FIELD+" TEXT, ")
            .append(ClienteModel.DIRECCION_FIELD+" TEXT, ")
            .append(ClienteModel.ID_EMPRESA_FIELD+" INTEGER, ")
            .append(ClienteModel.FECHA_NAC_FIELD+" TEXT, ")
            .append(ClienteModel.ID_ESTADO_CIVIL_FIELD+" INTEGER, ")
            .append(ClienteModel.TIENE_HIJOS_FIELD+" INTEGER ")
            .append(")");

    private final StringBuffer SQL_DELETE_TABLE_CLIENTE = new StringBuffer()
            .append("DROP TABLE IF EXISTS " + ClienteModel.TABLE_NAME);

    public Sesion4DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    /** EL metodo es ejecutado cuando se va crear por primera vez la Base de datos,
     * Aquí, podríamos poner algunas sentencias SQL como crear tablas, insertar algunos datos
     * iniciales a algunas tablas, entre otra cosas.
     * */
    @Override public void onCreate(SQLiteDatabase db) {

        //El metod execSQL() de la clase SQLiteDatabase ejecuta cualquier sentencia SQL.
        //recibe como parametro un String.

        db.execSQL(SQL_CREATE_TABLE_CLIENTE.toString());
    }

    /** Si por ejemplo, tenemos en el google play nuetro app con la version de base de datos 1, y un futuro cercano
     * lo actualizamos y en nuestra actualización tambien modificamos nuestra base de datos.
     * Entonces tenemos que aumentar la versión a la DB en la variable {DB_VERSION}, En ese momento, cuando el usuario actualize la version
     * de su app, el metodo pasará por aquí, ya no por onCreate(). Por eso, en este metodo debemos hacer cosas como eliminar las tables y volverlos
     * a crear, entre otras cosas.
     * */
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Elimina la tabla
        db.execSQL(SQL_DELETE_TABLE_CLIENTE.toString());

        //llamammos al metodo onCreate() para ejecutar su contenido que en este caso es crear la tabla cliente.
        onCreate(db);
    }
}

package com.jmarkstar.sesion4.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.sesion4.database.Sesion4DatabaseHelper;

/**
 * Created by jmarkstar on 12/05/2017.
 */

public class ClienteDao {

    private SQLiteDatabase mSQLiteDatabase;

    public ClienteDao(Context context){
        mSQLiteDatabase = new Sesion4DatabaseHelper(context).getWritableDatabase();
    }

}

package com.example.gerard.prueba_viernes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gerard on 03/07/2015.
 */
    public class clase_bd extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1; // NO TOCAR

        private static final String DATABASE_NAME = "project"; // NOMBRE DE LA BASE DE DATOS COMU PER TOTES LES FILES DE LA BD

        private static final String STATISTICS_TABLE_NAME = "log_in";

        private static final String STATISTICS_TABLE_CREATE = "CREATE TABLE " +
                STATISTICS_TABLE_NAME + " (username TEXT PRIMARY KEY, password TEXT, imatge TEXT,notificacio TEXT)";





    private static final String STATISTICS_TABLE_NAME_RANK = "ranking";

    private static final String STATISTICS_TABLE_CREATE_RANK = "CREATE TABLE " +
            STATISTICS_TABLE_NAME_RANK + " (id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,puntuacio INTEGER)";

        clase_bd(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override

        public void onCreate(SQLiteDatabase db) {

            db.execSQL(STATISTICS_TABLE_CREATE);
            db.execSQL(STATISTICS_TABLE_CREATE_RANK);

        }

        @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + STATISTICS_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + STATISTICS_TABLE_NAME_RANK);

            db.execSQL(STATISTICS_TABLE_CREATE);
            db.execSQL(STATISTICS_TABLE_CREATE_RANK);

        }

    }


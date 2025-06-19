package com.example.cblostankes.BD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cblostankes.INTERFAZ.EquipoDao;

@Database(entities = {ClaseEquipo.class, ClaseJugador.class}, version = 5) // Incrementa el número de versión
public abstract class AppDatabaseEquipos extends RoomDatabase {
    private static AppDatabaseEquipos CONEXION;

    public abstract EquipoDao equipoDao();

    //meter la comprobacion de si la base de datos ya esta creada
    public static AppDatabaseEquipos getDatabase(Context context) {
        if (CONEXION == null) {
            synchronized (AppDatabaseEquipos.class) {
                if (CONEXION == null) {
                    CONEXION = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabaseEquipos.class, "Lostankes")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return CONEXION;
    }
}
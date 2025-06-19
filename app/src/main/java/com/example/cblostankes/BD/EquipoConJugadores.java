package com.example.cblostankes.BD;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class EquipoConJugadores {
    @Embedded
    public ClaseEquipo equipo;

    @Relation(
            parentColumn = "id",
            entityColumn = "equipoId"
    )
    public List<ClaseJugador> jugadores;
}


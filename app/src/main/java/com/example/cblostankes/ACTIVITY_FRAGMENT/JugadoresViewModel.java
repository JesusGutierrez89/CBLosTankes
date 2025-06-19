package com.example.cblostankes.ACTIVITY_FRAGMENT;

import androidx.lifecycle.ViewModel;

import com.example.cblostankes.BD.ClaseJugador;

import java.util.List;

public class JugadoresViewModel extends ViewModel {
    private int idEquipo;
    private List<ClaseJugador> jugadores;

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public List<ClaseJugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<ClaseJugador> jugadores) {
        this.jugadores = jugadores;
    }
}

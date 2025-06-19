package com.example.cblostankes.INTERFAZ;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.BD.EquipoConJugadores;

import java.util.List;

@Dao
public interface EquipoDao {
    @Insert
    void insertarEquipo(ClaseEquipo equipo);

    @Insert
    void insertarJugador(ClaseJugador jugador);

    @Transaction
    @Query("SELECT * FROM equipos WHERE id = :equipoId")
    EquipoConJugadores getEquipoConJugadores(int equipoId);

    @Query("SELECT * FROM jugadores WHERE equipoId = :equipoId")
    List<ClaseJugador> obtenerJugadoresIdEquipo(int equipoId);

    @Query("SELECT * FROM equipos")
    public List<ClaseEquipo> obtenerTodosLosEquipos();

    @Query("SELECT * FROM jugadores")
    public List<ClaseJugador> obtenerTodosLosJugadores();

    @Update
    void actualizarEquipo(ClaseEquipo claseEquipo);

    @Update
    void actualizarJugador(ClaseJugador claseJugador);

    @Delete
    void borrarJugador(ClaseJugador jugador);

    @Query("select * from equipos where categoria = :categoria")
    List<ClaseEquipo> getEquiposByCategoria(String categoria);

    @Query("select * from equipos where modalidad = :modalidad")
    List<ClaseEquipo> getEquiposByModalidad(String modalidad);

    @Query("select * from jugadores where posicion = :posicion and equipoId = :idEquipo")
    List<ClaseJugador> getJugadoresByPosicion(String posicion, int idEquipo);

    @Query("select * from equipos where id==:id")
    ClaseEquipo getEquipoById(int id);

    @Delete
    void borrarEquipo(ClaseEquipo equipo);
}

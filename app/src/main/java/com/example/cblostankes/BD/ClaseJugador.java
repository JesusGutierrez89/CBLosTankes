package com.example.cblostankes.BD;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "jugadores",
        foreignKeys = @ForeignKey(
                entity = ClaseEquipo.class,
                parentColumns = "id",
                childColumns = "equipoId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index(value = "equipoId")}
)
public class ClaseJugador {
    @PrimaryKey(autoGenerate = true)
    private Integer  id;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String sexo;
    private String fecha_nacimiento;
    private int altura;
    private String telefono;
    private String correo_electronico;
    private String posicion;
    private String observaciones;
    private int equipoId; // Agregar este campo
    private byte[] fotografia;

    public ClaseJugador() {
    }
    public ClaseJugador(Integer id, String nombre, String primer_apellido,
                        String segundo_apellido, String sexo, String fecha_nacimiento,
                        int altura, String telefono, String posicion, String correo_electronico,
                        String observaciones, int equipoId, byte[] fotografia) {
        this.id = id;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.altura = altura;
        this.telefono = telefono;
        this.posicion = posicion;
        this.correo_electronico = correo_electronico;
        this.observaciones = observaciones;
        this.equipoId = equipoId; // Inicializar este campo
        this.fotografia = fotografia;
    }

    public Integer  getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }
}
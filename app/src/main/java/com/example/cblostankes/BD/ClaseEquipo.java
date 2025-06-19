package com.example.cblostankes.BD;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipos")
public class ClaseEquipo implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombreEquipo;
    private String patrocinador;
    private String categoria;
    private String modalidad;
    private String federado;
    private String dia_partido;
    private String hora_partido;
    private String entrenamientos;
    private byte[] fotografia; // Ruta de la imagen (si se usa en local o en un servidor)
    private String personaContacto;
    private String telefonoContacto;
    private int equipoId;

    public ClaseEquipo() {
    }

    public ClaseEquipo(int id, String nombreEquipo, String patrocinador, String categoria, String modalidad, String federado, String dia_partido, String hora_partido, String entrenamientos, byte[] fotografia, String personaContacto, String telefonoContacto, int equipoId) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.patrocinador = patrocinador;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.federado = federado;
        this.dia_partido = dia_partido;
        this.hora_partido = hora_partido;
        this.entrenamientos = entrenamientos;
        this.fotografia = fotografia;
        this.personaContacto = personaContacto;
        this.telefonoContacto = telefonoContacto;
        this.equipoId = equipoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getFederado() {
        return federado;
    }

    public void setFederado(String federado) {
        this.federado = federado;
    }

    public String getDia_partido() {
        return dia_partido;
    }

    public void setDia_partido(String dia_partido) {
        this.dia_partido = dia_partido;
    }

    public String getHora_partido() {
        return hora_partido;
    }

    public void setHora_partido(String hora_partido) {
        this.hora_partido = hora_partido;
    }

    public String getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(String entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }
    public static final Creator<ClaseEquipo> CREATOR = new Creator<ClaseEquipo>() {
        @Override
        public ClaseEquipo createFromParcel(Parcel in) {
            return new ClaseEquipo(in);
        }

        @Override
        public ClaseEquipo[] newArray(int size) {
            return new ClaseEquipo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Escribe los campos en el Parcel
    }
    protected ClaseEquipo(Parcel in) {
        // Lee los campos desde el Parcel
    }
}

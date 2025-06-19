package com.example.cblostankes.ACTIVIDAD_JUGADOR;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cblostankes.R;

public class InfoJugadorActivity extends AppCompatActivity {

    private ImageView imagenJugador;
    private TextView nombreJugador, apellidoJugador, fecha_nac, telefono, altura, correo, posicion, observaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jugador);

        imagenJugador = findViewById(R.id.imagenJugadorDetalle);
        nombreJugador = findViewById(R.id.nombreJugadorDetalle);
        apellidoJugador = findViewById(R.id.apellidoJugadorDetalle);
        fecha_nac = findViewById(R.id.fechaNac);
        altura = findViewById(R.id.alturaJu);
        posicion = findViewById(R.id.posicionJugadorDetalle);
        telefono = findViewById(R.id.telefonoJugadorDetalle);
        correo = findViewById(R.id.CorreoElec);
        observaciones = findViewById(R.id.Observaciones);

        // Obtener los datos del intent
        String nombre = getIntent().getStringExtra("nombre");
        String apellido1 = getIntent().getStringExtra("apellido1");
        String apellido2 = getIntent().getStringExtra("apellido2");
        String fechaNacimiento = getIntent().getStringExtra("fecha_nacimiento");
        int alturaJugador = getIntent().getIntExtra("altura", 0);
        String pos = getIntent().getStringExtra("posicion");
        String tel = getIntent().getStringExtra("telefono");
        String correoElectronico = getIntent().getStringExtra("correo");
        String observacionesJugador = getIntent().getStringExtra("observaciones");
        String imagenUriString = getIntent().getStringExtra("fotografiaUri");

        // Mostrar los datos en la interfaz
        nombreJugador.setText(nombre);
        apellidoJugador.setText(apellido1 + " " + apellido2);
        fecha_nac.setText("Fecha de Nacimiento: " + fechaNacimiento);
        altura.setText("Altura: " + alturaJugador);
        posicion.setText("Posición: " + pos);
        telefono.setText("Teléfono: " + tel);
        correo.setText("Correo: " + correoElectronico);
        observaciones.setText("Observaciones: " + observacionesJugador);

        if (imagenUriString != null) {
            Uri imagenUri = Uri.parse(imagenUriString);
            imagenJugador.setImageURI(imagenUri);
        } else {
            imagenJugador.setImageResource(R.drawable.falloescudo);
        }
    }
}
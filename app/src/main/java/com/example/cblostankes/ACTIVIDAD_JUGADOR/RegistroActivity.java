package com.example.cblostankes.ACTIVIDAD_JUGADOR;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.BD.EquipoConJugadores;
import com.example.cblostankes.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

public class RegistroActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private byte[] imagenSeleccionada;

    private EditText edtNombre, edtApellido1, edtApellido2, edtFecha, edAltura, edtTelefono, edtCorreo, edtObservaciones, edtEquipoId;
    private Spinner spinnerGenero, spinnerPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = findViewById(R.id.edtnombre);
        edtApellido1 = findViewById(R.id.edtApellido1);
        edtApellido2 = findViewById(R.id.edtApellido2);
        edtFecha = findViewById(R.id.edFecha);
        edAltura = findViewById(R.id.edAltura);
        edtTelefono = findViewById(R.id.edTelefono);
        edtCorreo = findViewById(R.id.edEmail);
        edtObservaciones = findViewById(R.id.edObservaciones);
        edtEquipoId = findViewById(R.id.edtEquipoId);
        spinnerGenero = findViewById(R.id.idSpinner);
        spinnerPosicion = findViewById(R.id.idSpinnerPosicion);
        Button btInfo = findViewById(R.id.btInfo);
        Button btInsertar = findViewById(R.id.btInsertar);
        Button btSeleccionarImagen = findViewById(R.id.btSeleccionarImagen);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterPosicion = ArrayAdapter.createFromResource(this,
                R.array.posicion_array, android.R.layout.simple_spinner_item);
        adapterPosicion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosicion.setAdapter(adapterPosicion);

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInfo();
            }
        });
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarJugador();
            }
        });
        btSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });
    }

    private void mostrarInfo() {
        String nombre = edtNombre.getText().toString();
        String apellido1 = edtApellido1.getText().toString();
        String apellido2 = edtApellido2.getText().toString();
        String altura = edAltura.getText().toString();
        double alturaCm = Double.parseDouble(altura) / 100;
        String genero = spinnerGenero.getSelectedItem().toString();
        String posicion = spinnerPosicion.getSelectedItem().toString();

        String mensaje;
        if (genero.equals("Femenino")) {
            mensaje = "La jugadora " + nombre + " " + apellido1 + " " + apellido2 + ", tiene una altura de " + alturaCm + " metros y juega en la posición de " + posicion;
        } else if (genero.equals("Masculino")) {
            mensaje = "El jugador " + nombre + " " + apellido1 + " " + apellido2 + ", tiene una altura de " + alturaCm + " metros y juega en la posición de " + posicion;
        } else {
            mensaje = "Le jugadore " + nombre + " " + apellido1 + " " + apellido2 + ", tiene una altura de " + alturaCm + " metros y juega en la posición de " + posicion;
        }

        new AlertDialog.Builder(this)
                .setTitle("Información del Jugador")
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void insertarJugador() {
        String nombre = edtNombre.getText().toString();
        String apellido1 = edtApellido1.getText().toString();
        String apellido2 = edtApellido2.getText().toString();
        String fecha = edtFecha.getText().toString();
        String altura = edAltura.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String correo = edtCorreo.getText().toString();
        String observaciones = edtObservaciones.getText().toString();
        String equipoId = edtEquipoId.getText().toString();
        String genero = spinnerGenero.getSelectedItem().toString();
        String posicion = spinnerPosicion.getSelectedItem().toString();

        ClaseJugador jugador = new ClaseJugador(null, nombre, apellido1, apellido2, genero, fecha, Integer.parseInt(altura), telefono, correo, posicion, observaciones, Integer.parseInt(equipoId), imagenSeleccionada);
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(this);
        Executors.newSingleThreadExecutor().execute(() -> insertarJugadorEnDB(db, jugador));
    }

    private void insertarJugadorEnDB(AppDatabaseEquipos db, ClaseJugador jugador) {
        List<ClaseEquipo> equiposExistentes = db.equipoDao().obtenerTodosLosEquipos();

        for (ClaseEquipo eq : equiposExistentes) {
            System.out.println("Equipo en DB: ID=" + eq.getEquipoId() + ", Nombre=" + eq.getNombreEquipo());
        }

        EquipoConJugadores equipo = db.equipoDao().getEquipoConJugadores(jugador.getEquipoId());
        if (equipo != null) {
            db.equipoDao().insertarJugador(jugador);
        } else {
            throw new IllegalArgumentException("EquipoId " + jugador.getEquipoId() + " no existe en la tabla ClaseEquipo.");
        }
    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imagenSeleccionada = convertirImagenABytes(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] convertirImagenABytes(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}
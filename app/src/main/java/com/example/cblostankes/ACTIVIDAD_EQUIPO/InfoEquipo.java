package com.example.cblostankes.ACTIVIDAD_EQUIPO;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.ACTIVIDAD_JUGADOR.JugadoresInfoActivity;
import com.example.cblostankes.R;

import java.util.concurrent.Executors;

public class InfoEquipo extends AppCompatActivity {

    private Button botonVolver;
    private Button botonJugadores;
    private ImageView imagenEquipo;
    private TextView nombreEquipo, patrocinadorEquipo, categoriaEquipo, modalidadEquipo,
            federadoEquipo, entrenamientosEquipo, contactoEquipo, telefonoContactoEquipo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_equipo);

        imagenEquipo = findViewById(R.id.imagenEquipoDetalle);
        nombreEquipo = findViewById(R.id.nombreEquipoDetalle);
        patrocinadorEquipo = findViewById(R.id.patrocinadorEquipoDetalle);
        categoriaEquipo = findViewById(R.id.categoriaEquipoDetalle);
        modalidadEquipo = findViewById(R.id.modalidadEquipoDetalle);
        federadoEquipo = findViewById(R.id.federadoEquipoDetalle);
        entrenamientosEquipo = findViewById(R.id.entrenamientosEquipoDetalle);
        contactoEquipo = findViewById(R.id.contactoEquipoDetalle);
        telefonoContactoEquipo = findViewById(R.id.telefonoContactoEquipoDetalle);

        int idEquipo = getIntent().getIntExtra("idEquipo", -1);

        if (idEquipo != -1) {
            cargarDatosEquipo(idEquipo);
        }

        botonVolver = findViewById(R.id.botonVolver);

        botonVolver.setOnClickListener(v -> {
            finish();
        });

        botonJugadores = findViewById(R.id.botonJugadores);

        botonJugadores.setOnClickListener(v -> {
            Intent intent = new Intent(InfoEquipo.this, JugadoresInfoActivity.class);
            intent.putExtra("idEquipo", idEquipo);
            startActivity(intent);
        });

        imagenEquipo.setOnClickListener(v -> {
            Intent intent = new Intent(InfoEquipo.this, JugadoresInfoActivity.class);
            intent.putExtra("idEquipo", idEquipo);
            startActivity(intent);
        });
    }
    private void mostrarDatosEquipo(ClaseEquipo claseEquipo) {
        nombreEquipo.setText(claseEquipo.getNombreEquipo());
        patrocinadorEquipo.setText("Patrocinador: " + claseEquipo.getPatrocinador());
        categoriaEquipo.setText("Categoría: " + claseEquipo.getCategoria());
        modalidadEquipo.setText("Modalidad: " + claseEquipo.getModalidad());
        federadoEquipo.setText("Federado: " + (claseEquipo.getFederado()));
        entrenamientosEquipo.setText("Entrenamientos: " + claseEquipo.getEntrenamientos());
        contactoEquipo.setText("Contacto: " + claseEquipo.getPersonaContacto());
        telefonoContactoEquipo.setText("Teléfono: " + claseEquipo.getTelefonoContacto());

        byte[] foto = claseEquipo.getFotografia();
        if (foto != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
            imagenEquipo.setImageBitmap(bitmap);
        } else {
            imagenEquipo.setImageResource(R.drawable.sinequipo);
        }
    }
    private void cargarDatosEquipo(int idEquipo) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getApplicationContext());
            ClaseEquipo claseEquipo = db.equipoDao().getEquipoById(idEquipo);

            runOnUiThread(() -> {
                if (claseEquipo != null) {
                    mostrarDatosEquipo(claseEquipo);
                }
            });
        });
    }
}
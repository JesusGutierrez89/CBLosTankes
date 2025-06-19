package com.example.cblostankes.ACTIVIDAD_EQUIPO;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cblostankes.R;

public class Modalidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modalidad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bmodalidad = getIntent().getExtras();

        int posicion = bmodalidad.getInt("item_position");


        TextView name = (TextView) findViewById(R.id.textViewTituloMod);
        TextView info = (TextView) findViewById(R.id.textViewInfoMod);
        if(posicion == 1){
            name.setText("Modalidad: Femenino");
            info.setText("Equipo: Los Aguiluchos\nEquipo:Los Jockets\nEquipo: La Mueteh\nEquipo: Espartanos Griegos\nEquipo: Los arbustos Espinosos");
        }else if(posicion == 2) {
            name.setText("Modalidad: Masculino");
            info.setText("Equipo: Los Indefinidos Errantes\n" +
                    "Equipo: Los Sayamans\n" +
                    "Equipo: Los pescados Furiosos\n" +
                    "Equipo: Los Lobos del Norte");
        }

    }
}
package com.example.cblostankes.ACTIVIDAD_EQUIPO;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cblostankes.R;

public class Categorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bcategorias = getIntent().getExtras();

        int posicion = bcategorias.getInt("item_position");


        TextView name = (TextView) findViewById(R.id.textViewTituloCat);
        TextView info = (TextView) findViewById(R.id.textViewInfoCat);
        if(posicion == 1){
            name.setText("Categoría: Escuela");
            info.setText("Equipo: Los Sayamans");
        }else if(posicion == 2){
            name.setText("Categoría: Benjamín");
            info.setText("Equipo: Los Indefinidos Errantes");
        } else if (posicion ==3) {
            name.setText("Categoría: Alevín");
            info.setText("Equipo: La Mueteh");
        }else if (posicion ==4) {
            name.setText("Categoría: Infantil");
            info.setText("Equipo: Los Jockets");
        }else if (posicion ==5) {
            name.setText("Categoría: Cadete");
            info.setText("Equipo: Los arbustos Espinosos\nEquipo: Los Aguiluchos ");
        }else if(posicion ==6) {
            name.setText("Categoría: Junior");
            info.setText("Equipo: Los pescados Furiosos\nEquipo: Los Lobos del Norte");
        }else if(posicion ==7) {
            name.setText("Categoría: Senior");
            info.setText("Equipo: Espartanos Griegos");
        }
    }

}
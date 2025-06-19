package com.example.cblostankes.ACTIVIDAD_JUGADOR;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.FRAGMENT_JUGADOR.JugadoresFondo;
import com.example.cblostankes.ACTIVITY_FRAGMENT.JugadoresViewModel;
import com.example.cblostankes.R;

import java.util.List;
import java.util.concurrent.Executors;

public class JugadoresInfoActivity extends AppCompatActivity {
    private TextView jugadoresInfo;
    private JugadoresViewModel jugadoresViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadores_info);

        jugadoresViewModel = new ViewModelProvider(this).get(JugadoresViewModel.class);

        // Obtener el idEquipo
        int idEquipo = getIntent().getIntExtra("idEquipo", -1);
        if (idEquipo != -1) {
            jugadoresViewModel.setIdEquipo(idEquipo);
            cargarJugadoresEquipo(idEquipo);
        }
    }

    private void cargarJugadoresEquipo(int idEquipo) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getApplicationContext());
            List<ClaseJugador> jugadores = db.equipoDao().obtenerJugadoresIdEquipo(idEquipo);

            runOnUiThread(() -> {
                if (jugadores != null && !jugadores.isEmpty()) {
                    StringBuilder info = new StringBuilder();
                    for (ClaseJugador jugador : jugadores) {
                        info.append(jugador.getNombre()).append(" ").append(jugador.getPrimer_apellido()).append("\n");
                    }
                } else {
                    jugadoresInfo.setText("No hay jugadores en este equipo");
                }

                // Almacenar jugadores en el ViewModel
                jugadoresViewModel.setJugadores(jugadores);
                cargarFragmento(jugadores);
            });
        });
    }

    private void cargarFragmento(List<ClaseJugador> jugadores) {
        JugadoresFondo jugadoresFondoFragment = new JugadoresFondo();

        // Usamos el ViewModel para acceder al idEquipo y jugadores
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, jugadoresFondoFragment);
        transaction.commit();
    }
}

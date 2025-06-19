package com.example.cblostankes.FRAGMENT_JUGADOR;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.ACTIVITY_FRAGMENT.JugadoresViewModel;
import com.example.cblostankes.R;
import com.example.cblostankes.RECYCLER.AdaptadorJugadores;

import java.util.List;

public class JugadoresFondo extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorJugadores adapter;
    private JugadoresViewModel jugadoresViewModel;

    public JugadoresFondo() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugadores_fondo, container, false);

        jugadoresViewModel = new ViewModelProvider(requireActivity()).get(JugadoresViewModel.class);

        recyclerView = view.findViewById(R.id.jugadorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Button botonFiltrarPosicion = view.findViewById(R.id.botonPosicion);
        botonFiltrarPosicion.setOnClickListener(v -> mostrarDialogoPosicion());

        // Recupera los jugadores del ViewModel
        List<ClaseJugador> jugadores = jugadoresViewModel.getJugadores();
        if (jugadores != null) {
            adapter = new AdaptadorJugadores(jugadores, getContext());
            recyclerView.setAdapter(adapter);
        }

        // Mostrar el ID del equipo con Toast

        return view;
    }

    private void cargarJugadores() {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            List<ClaseJugador> jugadores = db.equipoDao().obtenerJugadoresIdEquipo(jugadoresViewModel.getIdEquipo());

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorJugadores(jugadores, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void mostrarDialogoPosicion() {
        String[] posiciones = getResources().getStringArray(R.array.posicion_array);

        AlertDialog.Builder formarDialogo = new AlertDialog.Builder(requireContext());
        formarDialogo.setTitle(R.string.seleccionarCategoria)
                .setItems(posiciones, (dialog, which) -> {
                    filtrarEquiposPorPosicion(posiciones[which]);
                });

        formarDialogo.show();
    }

    private void filtrarEquiposPorPosicion(String posicionSeleccionada) {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            List<ClaseJugador> jugadores = db.equipoDao().getJugadoresByPosicion(posicionSeleccionada, jugadoresViewModel.getIdEquipo());

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorJugadores(jugadores, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}

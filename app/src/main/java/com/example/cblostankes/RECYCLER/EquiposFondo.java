package com.example.cblostankes.RECYCLER;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.R;

import java.util.List;

public class EquiposFondo extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorEquipo adapter;
    private List<ClaseEquipo> equipos;

    public EquiposFondo() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipos_fondo, container, false);

        recyclerView = view.findViewById(R.id.infoEquipoRecicler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Button botonFiltrarCategorias = view.findViewById(R.id.botonFiltrarCategoria);
        Button botonFiltrarModalidad = view.findViewById(R.id.botonFiltrarmodalidad);
        Button botonMostrarTodo = view.findViewById(R.id.botonMostrarTodo);
        botonFiltrarCategorias.setOnClickListener(v -> mostrarDialogoCategoria());
        botonFiltrarModalidad.setOnClickListener(v -> mostrarDialogoModalidad());
        botonMostrarTodo.setOnClickListener(v -> mostrarTodosLosEquipos());

        // Obtener los datos del Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            equipos = (List<ClaseEquipo>) bundle.getSerializable("equipos_key");
            adapter = new AdaptadorEquipo(equipos, getContext());
            recyclerView.setAdapter(adapter);
        } else {
            // Cargar equipos desde la base de datos si no hay datos en el Bundle
            cargarEquipos();
        }

        return view;
    }

    private void cargarEquipos() {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            equipos = db.equipoDao().obtenerTodosLosEquipos();

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorEquipo(equipos, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void mostrarDialogoCategoria() {
        String[] categorias = getResources().getStringArray(R.array.categorias);

        AlertDialog.Builder formarDialogo = new AlertDialog.Builder(requireContext());
        formarDialogo.setTitle(R.string.seleccionarCategoria)
                .setItems(categorias, (dialog, which) -> {
                    filtrarEquiposPorCategoria(categorias[which]);
                });

        formarDialogo.show();
    }

    private void mostrarDialogoModalidad() {
        String[] modalidades = getResources().getStringArray(R.array.modalidad);

        AlertDialog.Builder formarDialogo = new AlertDialog.Builder(requireContext());
        formarDialogo.setTitle(R.string.seleccionarModalidad)
                .setItems(modalidades, (dialog, which) -> {
                    filtrarEquiposPorModalidad(modalidades[which]);
                });

        formarDialogo.show();
    }

    private void filtrarEquiposPorCategoria(String categoriaSeleccionada) {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            equipos = db.equipoDao().getEquiposByCategoria(categoriaSeleccionada);

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorEquipo(equipos, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void filtrarEquiposPorModalidad(String modalidadSeleccionada) {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            equipos = db.equipoDao().getEquiposByModalidad(modalidadSeleccionada);

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorEquipo(equipos, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void mostrarTodosLosEquipos() {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(requireContext());
            equipos = db.equipoDao().obtenerTodosLosEquipos();

            requireActivity().runOnUiThread(() -> {
                adapter = new AdaptadorEquipo(equipos, getContext());
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    public void addEquipo(ClaseEquipo equipo) {
        equipos.add(equipo);
        adapter.notifyDataSetChanged();
    }
}
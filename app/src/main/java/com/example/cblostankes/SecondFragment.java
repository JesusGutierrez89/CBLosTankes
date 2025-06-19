package com.example.cblostankes;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.RECYCLER.AdaptadorEquipo;
import com.example.cblostankes.databinding.FragmentSecondBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class SecondFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private FragmentSecondBinding binding;
    private EditText edtEquipoId, edtEquipoNombre, edtPatrocinador, edtCategoria, edtModalidad, edtFederado, edtDiaPartido, edtHoraPartido, edtEntrenamientos, edtPersonaContacto, edtTelefonoContacto, edtEquipoId2;
    private byte[] imagenSeleccionada;
    private AdaptadorEquipo adaptadorEquipos;
    private List<ClaseEquipo> listaEquipos = new ArrayList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEquipoId = binding.edtEquipoId;
        edtEquipoNombre = binding.edtEquipoNombre;
        edtPatrocinador = binding.edtPatrocinador;
        edtCategoria = binding.edtCategoria;
        edtModalidad = binding.edtModalidad;
        edtFederado = binding.edtFederado;
        edtDiaPartido = binding.edtDiaPartido;
        edtHoraPartido = binding.edtHoraPartido;
        edtEntrenamientos = binding.edtEntrenamientos;
        edtPersonaContacto = binding.edtPersonaContacto;
        edtTelefonoContacto = binding.edtTelefonoContacto;
        edtEquipoId2 = binding.edtEquipoId2;
        Button btUpdateEquipo = binding.btUpdateEquipo;
        Button btDeleteEquipo = binding.btDeleteEquipo;
        Button btCrearEquipo = binding.btCrearEquipo;
        Button btInsertarImage = binding.btinsertarImage;

        adaptadorEquipos = new AdaptadorEquipo(listaEquipos, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.infoEquipoRecicler);
        recyclerView.setAdapter(adaptadorEquipos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btUpdateEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEquipo();
            }
        });

        btDeleteEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEquipo();
            }
        });

        btCrearEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearEquipo();
            }
        });

        btInsertarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarImagen();
            }
        });

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void crearEquipo() {
        String id = edtEquipoId.getText().toString();
        String nombreEquipo = edtEquipoNombre.getText().toString();
        String patrocinador = edtPatrocinador.getText().toString();
        String categoria = edtCategoria.getText().toString();
        String modalidad = edtModalidad.getText().toString();
        String federado = edtFederado.getText().toString();
        String diaPartido = edtDiaPartido.getText().toString();
        String horaPartido = edtHoraPartido.getText().toString();
        String entrenamientos = edtEntrenamientos.getText().toString();
        String personaContacto = edtPersonaContacto.getText().toString();
        String telefonoContacto = edtTelefonoContacto.getText().toString();

        ClaseEquipo equipo = new ClaseEquipo(Integer.parseInt(id), nombreEquipo, patrocinador, categoria, modalidad, federado, diaPartido, horaPartido, entrenamientos, imagenSeleccionada, personaContacto, telefonoContacto, Integer.parseInt(id));
        adaptadorEquipos.addEquipo(equipo);
        adaptadorEquipos.notifyDataSetChanged();
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getContext());
        Executors.newSingleThreadExecutor().execute(() -> insertarEquipoEnDB(db, equipo));
    }

    private void insertarEquipoEnDB(AppDatabaseEquipos db, ClaseEquipo equipo) {
        db.equipoDao().insertarEquipo(equipo);
    }

    private void insertarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
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

    private void updateEquipo() {
        String id = edtEquipoId.getText().toString();
        String nombreEquipo = edtEquipoNombre.getText().toString();
        String patrocinador = edtPatrocinador.getText().toString();
        String categoria = edtCategoria.getText().toString();
        String modalidad = edtModalidad.getText().toString();
        String federado = edtFederado.getText().toString();
        String diaPartido = edtDiaPartido.getText().toString();
        String horaPartido = edtHoraPartido.getText().toString();
        String entrenamientos = edtEntrenamientos.getText().toString();
        String personaContacto = edtPersonaContacto.getText().toString();
        String telefonoContacto = edtTelefonoContacto.getText().toString();

        ClaseEquipo equipo = new ClaseEquipo(Integer.parseInt(id), nombreEquipo, patrocinador, categoria, modalidad, federado, diaPartido, horaPartido, entrenamientos, imagenSeleccionada, personaContacto, telefonoContacto, Integer.parseInt(id));
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getContext());
        Executors.newSingleThreadExecutor().execute(() -> updateEquipoEnDB(db, equipo));
    }

    private void updateEquipoEnDB(AppDatabaseEquipos db, ClaseEquipo equipo) {
        db.equipoDao().actualizarEquipo(equipo);
    }

    private void deleteEquipo() {
        String id = edtEquipoId2.getText().toString();
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getContext());
        Executors.newSingleThreadExecutor().execute(() -> deleteEquipoEnDB(db, id));
    }

    private void deleteEquipoEnDB(AppDatabaseEquipos db, String id) {
        int numero = Integer.parseInt(id);
        ClaseEquipo equipo = new ClaseEquipo();
        equipo.setEquipoId(numero);

        db.equipoDao().borrarEquipo(equipo);
    }
}
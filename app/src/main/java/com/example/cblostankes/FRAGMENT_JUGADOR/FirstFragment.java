package com.example.cblostankes.FRAGMENT_JUGADOR;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.R;
import com.example.cblostankes.databinding.FragmentFirstBinding;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {
    private EditText edtId,edtId2, edtNombre, edtApellido1, edtApellido2, edtFecha, edAltura, edtTelefono, edtCorreo, edtObservaciones, edtEquipoId;
    private Spinner spinnerGenero, spinnerPosicion;


    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtId = binding.edtId;
        edtNombre = binding.edtnombre;
        edtApellido1 = binding.edtApellido1;
        edtApellido2 = binding.edtApellido2;
        edtFecha = binding.edFecha;
        edAltura = binding.edAltura;
        edtTelefono = binding.edTelefono;
        edtCorreo = binding.edEmail;
        edtObservaciones = binding.edObservaciones;
        edtEquipoId = binding.edtEquipoId;
        spinnerGenero = binding.idSpinner;
        spinnerPosicion = binding.idSpinnerPosicion;
        Button btUpdate = binding.btUpdate;
        edtId2 = binding.edtId2;
        Button btDelete = binding.btDelete;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.genero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterPosicion = ArrayAdapter.createFromResource(getContext(),
                R.array.posicion_array, android.R.layout.simple_spinner_item);
        adapterPosicion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosicion.setAdapter(adapterPosicion);


        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateJugador();
            }
        });

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteJugador();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateJugador() {
        String id = edtId.getText().toString();
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

        int random = (int) (Math.random() * 5) + 1;
        byte[] fotografia = null;
        switch (random) {
            case 1:
                int jugador1a = R.drawable.jugador1;
                fotografia = convertirImagenABytes(jugador1a);
                break;
            case 2:
                int jugador2b = R.drawable.jugador2;
                fotografia = convertirImagenABytes(jugador2b);
                break;
            case 3:
                int jugador3c = R.drawable.jugador3;
                fotografia = convertirImagenABytes(jugador3c);
                break;
            case 4:
                int jugador4d = R.drawable.jugador4;
                fotografia = convertirImagenABytes(jugador4d);
                break;
            case 5:
                int jugador5e = R.drawable.jugador5;
                fotografia = convertirImagenABytes(jugador5e);
                break;
        }

        ClaseJugador jugador = new ClaseJugador(Integer.parseInt(id), nombre, apellido1, apellido2, genero, fecha, Integer.parseInt(altura), telefono, correo, posicion, observaciones, Integer.parseInt(equipoId), fotografia);
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getContext());
        Executors.newSingleThreadExecutor().execute(() -> updateJugadorEnDB(db, jugador));
    }

    private void updateJugadorEnDB(AppDatabaseEquipos db, ClaseJugador jugador) {
        db.equipoDao().actualizarJugador(jugador);
    }

    private byte[] convertirImagenABytes(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
    private void deleteJugador() {
        String id = edtId2.getText().toString();
        AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getContext());
        Executors.newSingleThreadExecutor().execute(() -> deleteJugadorEnDB(db, id));
    }

    private void deleteJugadorEnDB(AppDatabaseEquipos db, String id) {
        int numero = Integer.parseInt(id);
        ClaseJugador jugador = new ClaseJugador();
        jugador.setId(numero);

        db.equipoDao().borrarJugador(jugador);
    }

}
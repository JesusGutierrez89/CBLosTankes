package com.example.cblostankes.FRAGMENT_EQUIPO;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.cblostankes.ACTIVIDAD_EQUIPO.Categorias;
import com.example.cblostankes.ACTIVIDAD_EQUIPO.Modalidad;
import com.example.cblostankes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Escudos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Escudos extends Fragment {
    private final int[] BOTONES_MENU_EQUIPOS = {
            R.id.Equipo1,
            R.id.Equipo2,
            R.id.Equipo3,
            R.id.Equipo4,
            R.id.Equipo5,
            R.id.Equipo6,
            R.id.Equipo7,
            R.id.Equipo8,
            R.id.Equipo9
    };
    private final int[] TEXTOS_EQUIPOS = {
            R.id.Equipo1Texto, // Añade los IDs de los TextView correspondientes
            R.id.Equipo2Texto, // Reemplaza y continúa para todos los equipos
            R.id.Equipo3Texto,
            R.id.Equipo4Texto,
            R.id.Equipo5Texto,
            R.id.Equipo6Texto,
            R.id.Equipo7Texto,
            R.id.Equipo8Texto,
            R.id.Equipo9Texto
    };

    private final int[] IMAGENES_EQUIPOS = {
            R.id.Equipo1imagen, // Añade los IDs de los ImageView correspondientes
            R.id.Equipo2imagen, // Reemplaza y continúa para todos los equipos
            R.id.Equipo3imagen,
            R.id.Equipo4imagen,
            R.id.Equipo5imagen,
            R.id.Equipo6imagen,
            R.id.Equipo7imagen,
            R.id.Equipo8imagen,
            R.id.Equipo9imagen

    };

    private int ultimoSeleccionado = -1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Escudos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Escudos.
     */
    // TODO: Rename and change types and number of parameters
    public static Escudos newInstance(String param1, String param2) {
        Escudos fragment = new Escudos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mimenu = inflater.inflate(R.layout.fragment_escudos, container, false);

        // Código existente para los botones
        ImageButton botonMenu;
        for (int i = 0; i < BOTONES_MENU_EQUIPOS.length; i++) {
            botonMenu = mimenu.findViewById(BOTONES_MENU_EQUIPOS[i]);
            final int queBoton = i; // variable para saber qué botón se ha pulsado
            botonMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Ocultar elementos del último botón seleccionado
                    if (ultimoSeleccionado != -1) {
                        mimenu.findViewById(TEXTOS_EQUIPOS[ultimoSeleccionado]).setVisibility(View.GONE);
                        mimenu.findViewById(IMAGENES_EQUIPOS[ultimoSeleccionado]).setVisibility(View.GONE);
                    }

                    // Mostrar elementos del botón actualmente seleccionado
                    mimenu.findViewById(TEXTOS_EQUIPOS[queBoton]).setVisibility(View.VISIBLE);
                    mimenu.findViewById(IMAGENES_EQUIPOS[queBoton]).setVisibility(View.VISIBLE);

                    // Actualizar el último botón seleccionado
                    ultimoSeleccionado = queBoton;
                }
            });
        }

        // Nuevo código para el Spinner
        Spinner spinnerCategoria = mimenu.findViewById(R.id.spinnerCategoria);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Intent intent = new Intent(getActivity(), Categorias.class);
                    intent.putExtra("item_position", position);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
        Spinner spinnerModalidad = mimenu.findViewById(R.id.spinnerMadalidad);

        spinnerModalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Intent intent = new Intent(getActivity(), Modalidad.class);
                    intent.putExtra("item_position", position);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        return mimenu;
    }
}
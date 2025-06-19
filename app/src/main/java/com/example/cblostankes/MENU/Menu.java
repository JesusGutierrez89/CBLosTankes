package com.example.cblostankes.MENU;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;

import com.example.cblostankes.CLASES_UNION.IntermediaEquipos;
import com.example.cblostankes.CLASES_UNION.IntermediaJugador;
import com.example.cblostankes.MainActivity;
import com.example.cblostankes.R;
import com.example.cblostankes.ACTIVIDAD_JUGADOR.RegistroActivity;

public class Menu extends Fragment {

    private final int[] BOTONES_MENU = {
            R.id.MenuPelota,
            R.id.JugadorBasket,
            R.id.DatosJugadores,
            R.id.escudoEquipos
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mimenu = inflater.inflate(R.layout.fragment_menu, container, false);

        ImageButton botonMenu;

        for (int i = 0; i < BOTONES_MENU.length; i++) {
            botonMenu = mimenu.findViewById(BOTONES_MENU[i]);
            final int queBoton = i;
            botonMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity estaActividad = getActivity();
                    if (estaActividad instanceof MainActivity) {
                        if (queBoton == 0) {
                            Intent intent = new Intent(estaActividad, Acerca_de.class);
                            startActivity(intent);
                        } else if (queBoton == 1) {
                            Intent intent = new Intent(estaActividad, RegistroActivity.class);
                            startActivity(intent);
                        } else if (queBoton == 2) {
                            Intent intent = new Intent(estaActividad, IntermediaJugador.class);
                            startActivity(intent);
                        } else if (queBoton == 3) {
                            Intent intent = new Intent(estaActividad, IntermediaEquipos.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }

        return mimenu;
    }
}
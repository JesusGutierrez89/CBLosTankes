package com.example.cblostankes.CLASES_UNION;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.R;
import com.example.cblostankes.RECYCLER.EquiposFondo;

import java.io.Serializable;
import java.util.List;

public class IntermediaEquipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedia);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Manejar los insets de la ventana si es necesario
            return insets;
        });

        if (savedInstanceState == null) {
            // Obtener datos de la base de datos en un hilo de fondo
            new FetchEquiposTask().execute();
        }
    }

    private class FetchEquiposTask extends AsyncTask<Void, Void, List<ClaseEquipo>> {
        @Override
        protected List<ClaseEquipo> doInBackground(Void... voids) {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(IntermediaEquipos.this);
            return db.equipoDao().obtenerTodosLosEquipos();
        }

        @Override
        protected void onPostExecute(List<ClaseEquipo> equipos) {
            if (equipos != null) {
                // Pasar los datos al fragmento
                EquiposFondo equiposFondoFragment = new EquiposFondo();
                Bundle bundle = new Bundle();
                bundle.putSerializable("equipos_key", (Serializable) equipos);
                equiposFondoFragment.setArguments(bundle);

                // Cargar el fragmento
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, equiposFondoFragment);
                transaction.commit();
            }
        }
    }
}
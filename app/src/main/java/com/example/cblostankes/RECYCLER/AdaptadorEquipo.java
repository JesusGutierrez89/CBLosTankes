package com.example.cblostankes.RECYCLER;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.ACTIVIDAD_EQUIPO.InfoEquipo;
import com.example.cblostankes.R;

import java.util.List;

public class AdaptadorEquipo extends RecyclerView.Adapter<AdaptadorEquipo.RecicleViewEquipo> {

    private final List<ClaseEquipo> claseEquipos;
    private final Context context;

    public AdaptadorEquipo(List<ClaseEquipo> claseEquipos, Context context) {
        this.claseEquipos = claseEquipos;
        this.context = context;
    }

    @Override
    public AdaptadorEquipo.RecicleViewEquipo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visualizacion_equipo, parent, false);
        return new RecicleViewEquipo(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorEquipo.RecicleViewEquipo holder, int position) {
        ClaseEquipo claseEquipo = claseEquipos.get(position);

        // Asegúrate de que estás obteniendo el nombre del equipo correctamente
        holder.nombreEquipo.setText(claseEquipo.getNombreEquipo());
        holder.categoriaEquipo.setText("Categoría: " + claseEquipo.getCategoria());

        String modalidad = "Modalidad: " + claseEquipo.getModalidad();
        holder.modalidad.setText(modalidad);

        String federado = "Federado: " + claseEquipo.getFederado();
        holder.federado.setText(federado);

        // Convertimos la imagen del equipo en un Bitmap para poder mostrarla
        byte[] foto = claseEquipo.getFotografia();

        if (foto != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
            holder.imagenEquipo.setImageBitmap(bitmap);
        } else {
            holder.imagenEquipo.setImageResource(R.drawable.sinequipo);
        }

        // Listener para mostrar información del equipo
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, InfoEquipo.class);
            intent.putExtra("idEquipo", claseEquipo.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return claseEquipos.size();
    }

    public void addEquipo(ClaseEquipo equipo) {
        claseEquipos.add(equipo);
        notifyItemInserted(claseEquipos.size() - 1);
    }

    public static class RecicleViewEquipo extends RecyclerView.ViewHolder {

        ImageView imagenEquipo;
        TextView nombreEquipo;
        TextView categoriaEquipo;
        TextView federado;
        TextView modalidad;

        public RecicleViewEquipo(View itemView) {
            super(itemView);
            imagenEquipo = itemView.findViewById(R.id.imagenEscudo);
            nombreEquipo = itemView.findViewById(R.id.nombreJugador);
            categoriaEquipo = itemView.findViewById(R.id.apellidosJugador);
            federado = itemView.findViewById(R.id.posicion);
            modalidad = itemView.findViewById(R.id.telefono);
        }
    }
}
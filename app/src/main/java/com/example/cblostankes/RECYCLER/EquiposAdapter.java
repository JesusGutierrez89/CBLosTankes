package com.example.cblostankes.RECYCLER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.R;

import java.util.List;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.EquipoViewHolder> {
    private List<ClaseEquipo> equipos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ClaseEquipo equipo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public EquipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visualizacion_equipo, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipoViewHolder holder, int position) {
        ClaseEquipo equipo = equipos.get(position);
        holder.bind(equipo, listener);
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreEquipo;
        public TextView categoriaEquipo;
        public ImageView imagenEscudo;

        public EquipoViewHolder(View itemView) {
            super(itemView);
            nombreEquipo = itemView.findViewById(R.id.nombreJugador);
            categoriaEquipo = itemView.findViewById(R.id.apellidosJugador);
            imagenEscudo = itemView.findViewById(R.id.imagenEscudo);
        }

        public void bind(final ClaseEquipo equipo, final OnItemClickListener listener) {
            nombreEquipo.setText(equipo.getNombreEquipo());
            categoriaEquipo.setText(equipo.getCategoria());
            // Set image and other fields...

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(equipo);
                    }
                }
            });
        }
    }
}
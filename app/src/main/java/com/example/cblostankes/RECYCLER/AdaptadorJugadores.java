package com.example.cblostankes.RECYCLER;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.ACTIVIDAD_JUGADOR.InfoJugadorActivity;
import com.example.cblostankes.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AdaptadorJugadores extends RecyclerView.Adapter<AdaptadorJugadores.RecicleViewJugador> {
    private final List<ClaseJugador> claseJugadores;
    private final Context context;

    public AdaptadorJugadores(List<ClaseJugador> claseJugadores, Context context) {
        this.claseJugadores = claseJugadores;
        this.context = context;
    }

    @Override
    public AdaptadorJugadores.RecicleViewJugador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visualizacion_jugador, parent, false);
        return new AdaptadorJugadores.RecicleViewJugador(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorJugadores.RecicleViewJugador holder, int position) {
        ClaseJugador claseJugador = claseJugadores.get(position);

        holder.nombreJugador.setText(claseJugador.getNombre());
        holder.apellidoJugador.setText(claseJugador.getPrimer_apellido() + " " + claseJugador.getSegundo_apellido());
        holder.posicion.setText(claseJugador.getPosicion());

        byte[] foto = claseJugador.getFotografia();
        if (foto != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
            holder.imagenJugador.setImageBitmap(bitmap);
        } else {
            holder.imagenJugador.setImageResource(R.drawable.falloescudo);
        }

        // Listener para mostrar información completa del jugador al hacer clic en la imagen
        holder.imagenJugador.setOnClickListener(v -> {
            Intent intent = new Intent(context, InfoJugadorActivity.class);
            intent.putExtra("idJugador", claseJugador.getId());
            intent.putExtra("nombre", claseJugador.getNombre());
            intent.putExtra("apellido1", claseJugador.getPrimer_apellido());
            intent.putExtra("apellido2", claseJugador.getSegundo_apellido());
            intent.putExtra("sexo", claseJugador.getSexo());
            intent.putExtra("altura", claseJugador.getAltura());
            intent.putExtra("fecha_nacimiento", claseJugador.getFecha_nacimiento());
            intent.putExtra("telefono", claseJugador.getTelefono());
            intent.putExtra("correo", claseJugador.getCorreo_electronico());
            intent.putExtra("posicion", claseJugador.getPosicion());
            intent.putExtra("observaciones", claseJugador.getObservaciones());

            Uri imagenUri = guardarImagenEnArchivo(claseJugador.getFotografia());
            if (imagenUri != null) {
                intent.putExtra("fotografiaUri", imagenUri.toString());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return claseJugadores.size();
    }

    private Uri guardarImagenEnArchivo(byte[] imagenBytes) {
        try {
            File file = new File(context.getCacheDir(), "jugador_imagen.png");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imagenBytes);
            fos.close();
            return FileProvider.getUriForFile(context, "com.example.cblostankes.fileprovider", file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class RecicleViewJugador extends RecyclerView.ViewHolder {

        ImageView imagenJugador;
        TextView nombreJugador;
        TextView apellidoJugador;
        TextView posicion;

        public RecicleViewJugador(View itemView) {
            super(itemView);
            imagenJugador = itemView.findViewById(R.id.imagenEscudo);
            nombreJugador = itemView.findViewById(R.id.nombreJugador);
            apellidoJugador = itemView.findViewById(R.id.apellidosJugador);
            posicion = itemView.findViewById(R.id.posicion);
        }
    }
}
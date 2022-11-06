package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNotificaciones extends RecyclerView.Adapter <AdapterNotificaciones.ViewHolderNotificaciones>{

    private Context context;
    private List<Publicacion> publicaciones;

    public AdapterNotificaciones(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderNotificaciones extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvNombreyapellido;
        TextView tvdescripcionulo;
        TextView tvfechanotificacion;


        public ViewHolderNotificaciones(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.notificacionfotoarticulo);
            tvNombreyapellido=itemView.findViewById(R.id.notificacionesusurio);
            tvdescripcionulo=itemView.findViewById(R.id.notificaciondescripcion);
            tvfechanotificacion=itemView.findViewById(R.id.aceptadosfecha);

        }
    }

    @NonNull
    @Override
    public AdapterNotificaciones.ViewHolderNotificaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gir_item_notificaciones,parent,false);
        return new AdapterNotificaciones.ViewHolderNotificaciones(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterNotificaciones.ViewHolderNotificaciones holder, int position) {
        holder.tvNombreyapellido.setText(publicaciones.get(position).getUsuario().getPersona().getNombre());
        holder.tvdescripcionulo.setText(publicaciones.get(position).getDescripcion());
        holder.tvfechanotificacion.setText("hace 1 dia");
       /* Picasso.get()
                .load(publicaciones.get(position).getIdusuario().getUrlImg())
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}
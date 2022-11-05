package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterAceptados extends RecyclerView.Adapter <AdapterAceptados.ViewHolderAceptados>{

    private Context context;
    private List<EPublicaciones> publicaciones;

    public AdapterAceptados (Context context, List<EPublicaciones> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderAceptados extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTitulo;
        TextView tvDescripcion;

        public ViewHolderAceptados(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.aceptadosfotoarticulo);
            tvTitulo=itemView.findViewById(R.id.aceptadosnombrearticulo);
            tvDescripcion=itemView.findViewById(R.id.aceptadosdescripcion);

        }
    }

    @NonNull
    @Override
    public AdapterAceptados.ViewHolderAceptados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gir_item_aceptados,parent,false);
        return new AdapterAceptados.ViewHolderAceptados(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterAceptados.ViewHolderAceptados holder, int position) {
        holder.tvTitulo.setText(publicaciones.get(position).getTitulo());
        holder.tvDescripcion.setText(publicaciones.get(position).getDescripcion());
        Picasso.get()
                .load(publicaciones.get(position).getUrlImg())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}

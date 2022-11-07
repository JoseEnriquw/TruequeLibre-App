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

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPublicacionesOfrecer extends RecyclerView.Adapter <AdapterPublicacionesOfrecer.ViewHolderPublicacionesOfrecer> {
    private Context context;
    private List<Publicacion> publicaciones;

    public AdapterPublicacionesOfrecer(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public ViewHolderPublicacionesOfrecer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_ofrecer_a_cambio,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicacionesOfrecer holder, int position) {
        holder.tvTitulo.setText(publicaciones.get(position).getNombre());
        holder.tvSubtitulo.setText(publicaciones.get(position).getDescripcion());
        Picasso.get()
                .load(publicaciones.get(position).getImagenes())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderPublicacionesOfrecer extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView tvTitulo;
        TextView tvSubtitulo;

        public ViewHolderPublicacionesOfrecer(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imgvPublicaciones);
            tvTitulo=itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo=itemView.findViewById(R.id.tvSubtitulo);

        }
    }
}

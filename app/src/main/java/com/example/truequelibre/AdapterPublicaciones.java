package com.example.truequelibre;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPublicaciones  extends RecyclerView.Adapter <AdapterPublicaciones.ViewHolderPublicaciones>{

    private Context context;
    private List<EPublicaciones> publicaciones;

    public AdapterPublicaciones(Context context, List<EPublicaciones> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    public  static  class ViewHolderPublicaciones extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTitulo;
        TextView tvSubtitulo;
        public ViewHolderPublicaciones(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imgvPublicaciones);
            tvTitulo=itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo=itemView.findViewById(R.id.tvSubtitulo);
        }
    }

    @NonNull
    @Override
    public ViewHolderPublicaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new ViewHolderPublicaciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicaciones holder, int position) {
       holder.tvTitulo.setText(publicaciones.get(position).getTitulo());
       holder.tvSubtitulo.setText(publicaciones.get(position).getSubtitulo());
        Picasso.get()
                .load(publicaciones.get(position).getUrlImg())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}

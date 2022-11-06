package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.EPublicaciones;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterArticulos extends RecyclerView.Adapter <AdapterArticulos.ViewHolderArticulos>{

    private Context context;
    private List<EPublicaciones> publicaciones;

    public AdapterArticulos(Context context, List<EPublicaciones> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderArticulos extends RecyclerView.ViewHolder
    {
        ImageView imfotoarticulo;
        ImageView imfotoperfil;
        TextView tvNombreyApellido;
        TextView tvNombre;
        TextView tvDescripcion;
        Button btnOpciones;


        public ViewHolderArticulos(@NonNull View itemView) {
            super(itemView);

            imfotoarticulo= itemView.findViewById(R.id.ivFotoarticulo);
            imfotoperfil= itemView.findViewById(R.id.ivFotoPerfilarticulo);
            tvNombreyApellido=itemView.findViewById(R.id.tvNombreApellidoComentarios);
            tvNombre=itemView.findViewById(R.id.idtitutloarticulo);
            tvDescripcion=itemView.findViewById(R.id.iddescripcionarticulo);
            btnOpciones= (Button) itemView.findViewById(R.id.btnver);
        }
    }

    @NonNull
    @Override
    public AdapterArticulos.ViewHolderArticulos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_articulo,parent,false);
        return new AdapterArticulos.ViewHolderArticulos(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterArticulos.ViewHolderArticulos holder, int position) {
        holder.tvDescripcion.setText(publicaciones.get(position).getDescripcion());
        holder.tvNombreyApellido.setText(publicaciones.get(position).getIdusuario().getDNI().getNombre());
        holder.tvNombre.setText(publicaciones.get(position).getNombre());
        Picasso.get()
                .load(publicaciones.get(position).getUrlImg())
                .into(holder.imfotoarticulo);
        Picasso.get()
                .load(publicaciones.get(position).getIdusuario().getUrlImg())
                .into(holder.imfotoperfil);

        holder.btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext().getApplicationContext(),DetalleArticulo.class);
                view.getContext().startActivity(intent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}


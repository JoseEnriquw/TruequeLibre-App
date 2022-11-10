package com.example.truequelibre;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.Categoria;

import java.io.ByteArrayInputStream;
import java.util.List;

public class AdapterCategorias extends RecyclerView.Adapter <AdapterCategorias.ViewHolderCategorias>{

    private Context context;
    private List<Categoria> publicaciones;

    public AdapterCategorias(Context context, List<Categoria> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    public  static  class ViewHolderCategorias extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTitulo;

        public ViewHolderCategorias(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.idimgcategorias);
            tvTitulo=itemView.findViewById(R.id.idcategoriastitulo);

        }
    }

    @NonNull
    @Override
    public AdapterCategorias.ViewHolderCategorias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_categorias,parent,false);
        return new AdapterCategorias.ViewHolderCategorias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategorias.ViewHolderCategorias holder, int position) {
        holder.tvTitulo.setText(publicaciones.get(position).getDescripcion());
        byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imageView.setImageBitmap(theImage);

        holder.imageView.setOnClickListener( new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     Intent intent= new Intent(view.getContext().getApplicationContext(),PublicacionesPorCategoria.class);
                                                     view.getContext().startActivity(intent);

                                                 }
                                             }
        );
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}
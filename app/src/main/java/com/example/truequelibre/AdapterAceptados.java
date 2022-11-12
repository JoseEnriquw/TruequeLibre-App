package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.Publicacion;

import java.io.ByteArrayInputStream;
import java.util.List;

public class AdapterAceptados extends RecyclerView.Adapter <AdapterAceptados.ViewHolderAceptados>{

    private Context context;
    private List<OfertasResponse> ofertas;

    public AdapterAceptados (Context context, List<OfertasResponse> ofertas) {
        this.context = context;
        this.ofertas = ofertas;
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
        holder.tvTitulo.setText(ofertas.get(position).getNombre_ofertante());
        holder.tvDescripcion.setText(ofertas.get(position).getDescripcion_ofertante());
        if (ofertas.get(position).getImagen_ofertante() != null){
            byte[] byteArray =  Base64.decode(ofertas.get(position).getImagen_ofertante(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(theImage);
        }
    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }



}

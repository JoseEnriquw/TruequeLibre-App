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

import com.example.truequelibre.Entity.Oferta;
import com.example.truequelibre.Entity.OfertasResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public class AdapterEnviados extends RecyclerView.Adapter <AdapterEnviados.ViewHolderEnviados>{

    private Context context;
    private List<OfertasResponse> ofertas;

    public AdapterEnviados(Context context, List<OfertasResponse> ofertas) {
        this.context = context;
        this.ofertas = ofertas;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderEnviados extends RecyclerView.ViewHolder
    {
        ImageView imageViewarticuloofertado;
        ImageView imageViewarticuloquerido;
        TextView tvNombreofertado;
        TextView tvNombrequerido;

        public ViewHolderEnviados(@NonNull View itemView) {
            super(itemView);

            imageViewarticuloofertado= itemView.findViewById(R.id.imgvofertado);
            imageViewarticuloquerido= itemView.findViewById(R.id.imgvquerido);
            tvNombreofertado=itemView.findViewById(R.id.tvTituloofertado);
            tvNombrequerido=itemView.findViewById(R.id.tvTituloquerido);


        }
    }

    @NonNull
    @Override
    public AdapterEnviados.ViewHolderEnviados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gir_item_enviados,parent,false);
        return new AdapterEnviados.ViewHolderEnviados(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterEnviados.ViewHolderEnviados holder, int position) {
        holder.tvNombreofertado.setText(ofertas.get(position).getNombre_ofertante());
        holder.tvNombrequerido.setText(ofertas.get(position).getNombre_principal());
        if (ofertas.get(position).getImagen_ofertante() != null & ofertas.get(position).getImagen_principal() != null){
            byte[] byteArray =  Base64.decode(ofertas.get(position).getImagen_ofertante(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageViewarticuloofertado.setImageBitmap(theImage);

            byte[] byteArrayp =  Base64.decode(ofertas.get(position).getImagen_principal(), Base64.DEFAULT);
            ByteArrayInputStream imageStreamp = new ByteArrayInputStream(byteArrayp);
            Bitmap theImagep = BitmapFactory.decodeStream(imageStreamp);
            holder.imageViewarticuloquerido.setImageBitmap(theImagep);
        }


    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }



}

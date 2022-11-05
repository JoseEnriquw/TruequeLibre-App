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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterEnviados extends RecyclerView.Adapter <AdapterEnviados.ViewHolderEnviados>{

    private Context context;
    private List<EOferta> ofertas;

    public AdapterEnviados(Context context, List<EOferta> ofertas) {
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
        holder.tvNombreofertado.setText(ofertas.get(position).getPublicacionprincipal().getTitulo());
        holder.tvNombrequerido.setText(ofertas.get(position).getOfertas().getTitulo());
        Picasso.get()
                .load(ofertas.get(position).getOfertas().getUrlImg())
                .into(holder.imageViewarticuloquerido);

        Picasso.get()
                .load(ofertas.get(position).getPublicacionprincipal().getUrlImg())
                .into(holder.imageViewarticuloofertado);


    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }



}

package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class AdapterRecibidos extends RecyclerView.Adapter <AdapterRecibidos.ViewHolderRecibidos>{

    private Context context;
    private List<Oferta> ofertas;

    public AdapterRecibidos(Context context, List<Oferta> ofertas) {
        this.context = context;
        this.ofertas = ofertas;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderRecibidos extends RecyclerView.ViewHolder
    {
        ImageView imageViewarticulo;
        TextView tvArticulo;
        TextView tvdescripcionbreveo;
        FloatingActionButton btnaceptar;
        FloatingActionButton btnrechazar;


        public ViewHolderRecibidos(@NonNull View itemView) {
            super(itemView);

            imageViewarticulo= itemView.findViewById(R.id.recibidosfotoarticulo);
            tvArticulo=itemView.findViewById(R.id.recibidosnombrearticulo);
            tvdescripcionbreveo=itemView.findViewById(R.id.recibidosdescripcion);
            btnaceptar=itemView.findViewById(R.id.btnrecibidosaceptar);
            btnrechazar=itemView.findViewById(R.id.btnrecibidosrechazar);

        }
    }

    @NonNull
    @Override
    public AdapterRecibidos.ViewHolderRecibidos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gir_item_recibidos,parent,false);
        return new AdapterRecibidos.ViewHolderRecibidos(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterRecibidos.ViewHolderRecibidos holder, int position) {
        holder.tvArticulo.setText(ofertas.get(position).getOfertas().getNombre());
        holder.tvdescripcionbreveo.setText(ofertas.get(position).getOfertas().getDescripcion());
       /* Picasso.get()
                .load(ofertas.get(position).getOfertas().getImagenes())
                .into(holder.imageViewarticulo);*/

        holder.btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ABRIR CHAT
                Intent intent= new Intent(view.getContext().getApplicationContext(),Chat.class);
                view.getContext().startActivity(intent);
            }
        });

        holder.btnrechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ELIMINAR OFERTA
            }
        });
    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }



}

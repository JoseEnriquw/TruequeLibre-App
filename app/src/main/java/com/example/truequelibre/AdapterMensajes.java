package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajes extends RecyclerView.Adapter<AdapterMensajes.ViewHolderMensajes> {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private String usuarioActual;

    List<Mensaje> listaMensajes = new ArrayList<>();
    private Context context;

    public AdapterMensajes(List<Mensaje> listaMensajes, Context context) {
        this.listaMensajes = listaMensajes;
        this.context = context;
    }

    public AdapterMensajes(Context context) {

        this.context = context;
    }

    public void addMensaje(Mensaje mensaje){
        listaMensajes.add(mensaje);
        notifyDataSetChanged();
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderMensajes extends RecyclerView.ViewHolder
    {
        private TextView usuario;
        private TextView mensaje;

        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);
            usuario = (TextView) itemView.findViewById(R.id.tvUsuarioChat);
            mensaje = (TextView) itemView.findViewById(R.id.tvMensajeChat);
        }

        public TextView getUsuario() {
            return usuario;
        }

        public void setUsuario(TextView usuario) {
            this.usuario = usuario;
        }

        public TextView getMensaje() {
            return mensaje;
        }

        public void setMensaje(TextView mensaje) {
            this.mensaje = mensaje;
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    @NonNull
    @Override
    public ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_view_mensaje,parent,false);
        return new AdapterMensajes.ViewHolderMensajes(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderMensajes holder, int position) {
        holder.usuario.setText(listaMensajes.get(position).getUsuario());
        holder.mensaje.setText(listaMensajes.get(position).getMensaje());
    }




}

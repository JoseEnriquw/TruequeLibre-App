package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.Mensaje;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajes extends RecyclerView.Adapter<AdapterMensajes.ViewHolderMensajes> {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private String usuarioActual;

    List<Mensaje> listaMensajes = new ArrayList<>();
    private Context context;

    private Integer idUsuarioActual;

    public AdapterMensajes(List<Mensaje> listaMensajes, Context context) {
        this.listaMensajes = listaMensajes;
        this.context = context;
    }

    public AdapterMensajes(Context context, Integer idUsuarioActual) {
        this.context = context;
        this.idUsuarioActual = idUsuarioActual;
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
        private ConstraintLayout cll;
        private TextView mensaje;
        private ImageView imgPerfil;
        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);
            cll = (ConstraintLayout) itemView.findViewById(R.id.cclayout);
            imgPerfil = (ImageView) itemView.findViewById(R.id.small_profile_img);
            mensaje = (TextView) itemView.findViewById(R.id.tvMessageContent);
        }

        public ConstraintLayout getCll() {
            return cll;
        }

        public void setCll(ConstraintLayout cll) {
            this.cll = cll;
        }

        public TextView getMensaje() {
            return mensaje;
        }

        public void setMensaje(TextView mensaje) {
            this.mensaje = mensaje;
        }

        public ImageView getImgPerfil() {
            return imgPerfil;
        }

        public void setImgPerfil(ImageView imgPerfil) {
            this.imgPerfil = imgPerfil;
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    @NonNull
    @Override
    public ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_holder,parent,false);
        return new AdapterMensajes.ViewHolderMensajes(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderMensajes holder, int position) {
        holder.mensaje.setText(listaMensajes.get(position).getMensaje());
        if(listaMensajes.get(position).getFotoUsuario() != null)  holder.imgPerfil.setImageBitmap(ImagenConverter.convertByteToBitmap(listaMensajes.get(position).getFotoUsuario()));

        ConstraintLayout constraintLayout = holder.cll;
        if (idUsuarioActual == listaMensajes.get(position).getUsuario()) {

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.LEFT);
            constraintSet.clear(R.id.tvMessageContent, ConstraintSet.LEFT);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.RIGHT, R.id.cclayout, ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.tvMessageContent, ConstraintSet.RIGHT, R.id.profile_cardView, ConstraintSet.LEFT, 0);
            constraintSet.applyTo(constraintLayout);
        } else {

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.tvMessageContent, ConstraintSet.RIGHT);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.LEFT, R.id.cclayout, ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.tvMessageContent, ConstraintSet.LEFT, R.id.profile_cardView, ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(constraintLayout);
        }
    }



}

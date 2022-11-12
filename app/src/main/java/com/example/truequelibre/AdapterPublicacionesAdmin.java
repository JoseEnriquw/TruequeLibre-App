package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPublicacionesAdmin extends RecyclerView.Adapter <AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin>{

    private Context context;
    private List<Publicacion> publicaciones;
    IPublicacionService service;

    public AdapterPublicacionesAdmin(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderPublicacionesAdmin extends RecyclerView.ViewHolder
    {
        ImageView ivFotoArticulo;
        TextView tvNombreApellido;
        ImageView ivFotoPerfilArticulo;
        TextView tvTitulo;
        TextView tvDescripcion;
        TextView tvInteres;
        Button btnVer;

        public ViewHolderPublicacionesAdmin(@NonNull View itemView) {
            super(itemView);

            ivFotoArticulo= (ImageView) itemView.findViewById(R.id.ivFotoarticulo);
            tvNombreApellido=(TextView) itemView.findViewById(R.id.tvNombreApellidoComentarios);
            ivFotoPerfilArticulo=(ImageView) itemView.findViewById(R.id.ivFotoPerfilarticulo);
            tvTitulo=(TextView) itemView.findViewById(R.id.idtitutloarticulo);
            tvDescripcion=(TextView) itemView.findViewById(R.id.iddescripcionarticulo);
            btnVer= (Button) itemView.findViewById(R.id.btnver);

        }
    }

    @NonNull
    @Override
    public AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_articulo,parent,false);
        return new AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin holder, @SuppressLint("RecyclerView") int position) {
        holder.tvDescripcion.setText(publicaciones.get(position).getDescripcion());
        holder.tvNombreApellido.setText(publicaciones.get(position).getUsuario().getNombreApellido());
        holder.tvTitulo.setText(publicaciones.get(position).getNombre());
        if (publicaciones.get(position).getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.ivFotoArticulo.setImageBitmap(theImage);
        }

        if (publicaciones.get(position).getUsuario().getFotoPerfil() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getUsuario().getFotoPerfil(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.ivFotoPerfilArticulo.setImageBitmap(theImage);
        }

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext().getApplicationContext(),AprobarPublicaciones.class);
                intent.putExtra("idPublicacion",publicaciones.get(position).getId());
               // intent.putExtra("idUsuario",idUsuario);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }
}


package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.GetAllByCategoriaFilterRequest;
import com.example.truequelibre.Entity.GetAllByCategoriaRequest;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterArticulos extends RecyclerView.Adapter <AdapterArticulos.ViewHolderArticulos>{

    private Context context;
    private List<Publicacion> publicaciones;
    IPublicacionService service;

    public AdapterArticulos(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
        service= Apis.getPublicacionService();
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
        holder.tvNombreyApellido.setText(publicaciones.get(position).getUsuario().getNombreApellido());
        holder.tvNombre.setText(publicaciones.get(position).getNombre());
        if (publicaciones.get(position).getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imfotoarticulo.setImageBitmap(theImage);
        }

        if (publicaciones.get(position).getUsuario().getFotoPerfil() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getUsuario().getFotoPerfil(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imfotoperfil.setImageBitmap(theImage);
        }

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

    public void filtrar(String search,Integer idUsuario,Integer idCategoria)
    {
        if(!search.isEmpty()) {
            GetAllByCategoriaFilterRequest request = new GetAllByCategoriaFilterRequest(idCategoria, search, idUsuario);
            Call<List<Publicacion>> call = service.getAllByCategoriaFilter(request);

            call.enqueue(new Callback<List<Publicacion>>() {
                @Override
                public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                    if (response.isSuccessful()) {
                        publicaciones = response.body();
                        notifyDataSetChanged();
                    } else {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Error>>() {
                        }.getType();
                        List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                        for (Error item : message) {
                            Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                    System.out.println(t.getCause() + " \n" + t.getMessage());
                    Toast.makeText(context, "Hubo un error al traer los datos de la base de datos :(", Toast.LENGTH_LONG);
                }
            });
        }
        else
        {
            GetAllByCategoriaRequest request= new GetAllByCategoriaRequest(idCategoria,idUsuario);
            Call<List<Publicacion>> call =service.getPublicacionesByCategoria(request);


            call.enqueue(new Callback<List<Publicacion>>() {
                @Override
                public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                    if(response.isSuccessful()) {
                        publicaciones = response.body();
                        notifyDataSetChanged();
                    }
                    else
                    {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Error>>() {}.getType();
                        List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                        for (Error item: message) {
                            Toast.makeText(context,item.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                    System.out.println(t.getCause()+ " \n"+t.getMessage());
                    Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG);
                }
            });
        }
    }



}


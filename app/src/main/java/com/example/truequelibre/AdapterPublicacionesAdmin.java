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
        ImageView imageView;
        TextView tvNombreApellido;
        TextView tvNombre;
        TextView tvDescripcion;
        TextView tvCondicion;
        TextView tvInteres;
        Button btnAceptar;
        Button btnRechazar;

        public ViewHolderPublicacionesAdmin(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.detallefotoperiladmin);
            tvNombreApellido=itemView.findViewById(R.id.detallenombreapellidoadmin);
            tvNombre=itemView.findViewById(R.id.detalletituloadmin);
            tvDescripcion=itemView.findViewById(R.id.detallecondicionadmin);
            tvCondicion=itemView.findViewById(R.id.detallecondicionadmin);
            tvInteres=itemView.findViewById(R.id.tvLeinteresaarticuloadmin);

            btnAceptar= itemView.findViewById(R.id.btnaceptaradmin);
            btnRechazar=  itemView.findViewById(R.id.btnrechazaradmin);

        }
    }

    @NonNull
    @Override
    public AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_aceptar_rechazar_publicacion,parent,false);
        return new AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterPublicacionesAdmin.ViewHolderPublicacionesAdmin holder, @SuppressLint("RecyclerView") int position) {
        service= Apis.getPublicacionService();

        holder.tvNombreApellido.setText(publicaciones.get(position).getUsuario().getNombreApellido());
        holder.tvNombre.setText(publicaciones.get(position).getNombre());
        holder.tvDescripcion.setText(publicaciones.get(position).getDescripcion());
        holder.tvCondicion.setText(publicaciones.get(position).getCondicion());
        holder.tvInteres.setText(publicaciones.get(position).getInteres());



        if (publicaciones.get(position).getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(theImage);
        }


        holder.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<ResponseBody> call = service.updateAdmin(publicaciones.get(position).getId(), new PublicacionEditarRequestAdmin(1));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(context.getApplicationContext(), "Publicacion modificada con exito!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Error>>() {}.getType();
                            List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                            for (Error item: message) {
                                Toast.makeText(context.getApplicationContext(),item.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getCause()+ " \n"+t.getMessage());
                        Toast.makeText(context.getApplicationContext(),"Error al modificar la publicación!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        holder.btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = service.updateAdmin(publicaciones.get(position).getId(), new PublicacionEditarRequestAdmin(5));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(context.getApplicationContext(),"Publicacion modificada con exito!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Error>>() {}.getType();
                            List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                            for (Error item: message) {
                                Toast.makeText(context.getApplicationContext(),item.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getCause()+ " \n"+t.getMessage());
                        Toast.makeText(context.getApplicationContext(),"Error al modificar la publicación!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}


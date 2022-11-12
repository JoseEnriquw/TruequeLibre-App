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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.CreateOfertaRequest;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IOfertaService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterOfrecerACambio extends RecyclerView.Adapter <AdapterOfrecerACambio.ViewHolderPublicacionesOfrecer>{

    private Context context;
    private List<Publicacion> publicaciones;
    private Integer idPublicacion;
    private IOfertaService service;

    public AdapterOfrecerACambio(Context context, List<Publicacion> publicaciones, Integer idPublicacion) {
        this.context = context;
        this.publicaciones = publicaciones;
        this.idPublicacion = idPublicacion;
        service = Apis.getOfertaService();
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderPublicacionesOfrecer extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTitulo;
        TextView tvSubtitulo;


        public ViewHolderPublicacionesOfrecer(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imgvPublicacionOfrecerItem);
            tvTitulo=itemView.findViewById(R.id.tvTituloPublicacionOfrecer);
            tvSubtitulo=itemView.findViewById(R.id.tvSubtituloPublicacionOfrecer);
        }
    }

    @NonNull
    @Override
    public ViewHolderPublicacionesOfrecer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item_ofrecer,parent,false);
        return new AdapterOfrecerACambio.ViewHolderPublicacionesOfrecer(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicacionesOfrecer holder,@SuppressLint("RecyclerView")  int position) {
        holder.tvTitulo.setText(publicaciones.get(position).getNombre());
        holder.tvSubtitulo.setText(publicaciones.get(position).getDescripcion());
        if (publicaciones.get(position).getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(theImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateOfertaRequest ofertaRequest = new CreateOfertaRequest(idPublicacion, publicaciones.get(position).getId());
                Call<ResponseBody> callOfrecer = service.createOferta(ofertaRequest);

                callOfrecer.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context,"Se ha creado la oferta, debe esperar a que el usuario la acepte",Toast.LENGTH_LONG).show();
                        }
                        else {
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
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

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

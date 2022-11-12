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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.UpdateOfertaVM;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IOfertaService;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecibidos extends RecyclerView.Adapter <AdapterRecibidos.ViewHolderRecibidos>{

    private Context context;
    private List<OfertasResponse> ofertas;
    IOfertaService service;

    public AdapterRecibidos(Context context, List<OfertasResponse> ofertas) {
        this.context = context;
        this.ofertas = ofertas;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderRecibidos extends RecyclerView.ViewHolder
    {
        ImageView imageViewarticulo;
        TextView tvArticulo;
        TextView tvdescripcionbreveo;
        TextView recibidosNombrepublicacionprincipal;
        FloatingActionButton btnaceptar;
        FloatingActionButton btnrechazar;


        public ViewHolderRecibidos(@NonNull View itemView) {
            super(itemView);

            imageViewarticulo= itemView.findViewById(R.id.recibidosfotoarticulo);
            tvArticulo=itemView.findViewById(R.id.recibidosnombrearticulo);
            recibidosNombrepublicacionprincipal=itemView.findViewById(R.id.recibidosNombrepublicacionprincipal);
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
    public void onBindViewHolder(@NonNull AdapterRecibidos.ViewHolderRecibidos holder,@SuppressLint("RecyclerView") int position) {
        service= Apis.getOfertaService();
        holder.tvArticulo.setText(ofertas.get(position).getNombre_ofertante());
        holder.tvdescripcionbreveo.setText(ofertas.get(position).getDescripcion_ofertante());
        holder.recibidosNombrepublicacionprincipal.setText(ofertas.get(position).getNombre_principal());
        if (ofertas.get(position).getImagen_ofertante() != null){
            byte[] byteArray =  Base64.decode(ofertas.get(position).getImagen_ofertante(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageViewarticulo.setImageBitmap(theImage);
        }

        holder.btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ABRIR CHAT
            //    Intent intent= new Intent(view.getContext().getApplicationContext(),Chat.class);
            //    view.getContext().startActivity(intent);
                UpdateOfertaVM estado = new UpdateOfertaVM(4);
                Call<ResponseBody> updateRequest = service.updateOferta(ofertas.get(position).getId(),estado);
                updateRequest.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful())
                            {
                                //ofertas.remove(position);
                                ofertas.removeAll(ofertas.stream().filter(x-> x.getId() == ofertas.get(position).getId()).collect(Collectors.toList()));
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
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println(t.getCause()+ " \n"+t.getMessage());
                            Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(", Toast.LENGTH_LONG).show();
                        }
                    });

            }
        });

        holder.btnrechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> deleteRequest = service.deleteOferta(ofertas.get(position).getId());
                deleteRequest.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful())
                        {
                            ofertas.remove(position);
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
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getCause()+ " \n"+t.getMessage());
                        Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }



}

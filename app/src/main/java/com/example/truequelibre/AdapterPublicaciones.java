package com.example.truequelibre;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionEditarRequest;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPublicaciones  extends RecyclerView.Adapter <AdapterPublicaciones.ViewHolderPublicaciones>{

    private Context context;
    private List<Publicacion> publicaciones;
    IPublicacionService service;
    private AlertDialog.Builder builder;


    public AdapterPublicaciones(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderPublicaciones extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTitulo;
        TextView tvSubtitulo;
        ImageButton btnOpciones;
        MenuBuilder menuBuilder;
        MenuInflater menuInflater;

        public ViewHolderPublicaciones(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imgvPublicaciones);
            tvTitulo=itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo=itemView.findViewById(R.id.tvSubtitulo);
            btnOpciones= (ImageButton) itemView.findViewById(R.id.ibMoreOptions);
            menuBuilder=new MenuBuilder(itemView.getContext());
            menuInflater=new MenuInflater(itemView.getContext());
        }
    }

    @NonNull
    @Override
    public ViewHolderPublicaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new ViewHolderPublicaciones(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicaciones holder, @SuppressLint("RecyclerView") int position) {
        service= Apis.getPublicacionService();
        builder = new AlertDialog.Builder(context);
       holder.tvTitulo.setText(publicaciones.get(position).getNombre());
       holder.tvSubtitulo.setText(publicaciones.get(position).getDescripcion());
       // ByteArrayInputStream imageStream = new ByteArrayInputStream(publicaciones.get(position).getImagenes());
        //Bitmap theImage = BitmapFactory.decodeStream(imageStream);
       // holder.imageView.setImageBitmap(theImage);
        /*Picasso.get()
                .load(publicaciones.get(position).getUrlImg())
                .into(holder.imageView);*/

        if (publicaciones.get(position).getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicaciones.get(position).getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(theImage);
        }

        holder.menuInflater.inflate(R.menu.popup_menu_publicaciones,holder.menuBuilder);

        holder.btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MenuPopupHelper optionMenu=new MenuPopupHelper(view.getContext(),holder.menuBuilder,view);
                optionMenu.setForceShowIcon(true);

                holder.menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.itemEditar:
                                Intent i = new Intent(view.getContext().getApplicationContext(),EditarPublicaciones.class);
                                i.putExtra("idPublicacion", publicaciones.get(position).getId());
                                i.putExtra("idUsuario", publicaciones.get(position).getUsuario().getIdUsuario());
                                view.getContext().startActivity(i);
                                break;
                            case R.id.itemEliminar:
                                builder.setMessage(" ")
                                        .setCancelable(false)
                                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Call<ResponseBody> deleteRequest = service.deletePublicacion(publicaciones.get(position).getId());
                                                deleteRequest.enqueue(new Callback<ResponseBody>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                        if(response.isSuccessful())
                                                        {
                                                            publicaciones.remove(position);
                                                            notifyItemRemoved(position);
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
                                        })
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //  Action for 'NO' Button
                                                dialog.cancel();
                                                Toast.makeText(context,"Cancelado",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                //Creating dialog box
                                AlertDialog alert = builder.create();
                                //Setting the title manually
                                alert.setTitle("Desea eliminar esta publicacion?");
                                alert.show();

                                System.out.println("Eliminar------------------------");
                                break;
                            default:
                                return false;
                        }
                        return true;
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }



}

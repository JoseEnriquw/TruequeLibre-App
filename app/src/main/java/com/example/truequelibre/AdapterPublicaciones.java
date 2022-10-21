package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPublicaciones  extends RecyclerView.Adapter <AdapterPublicaciones.ViewHolderPublicaciones>{

    private Context context;
    private List<EPublicaciones> publicaciones;

    public AdapterPublicaciones(Context context, List<EPublicaciones> publicaciones) {
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
    public void onBindViewHolder(@NonNull ViewHolderPublicaciones holder, int position) {
       holder.tvTitulo.setText(publicaciones.get(position).getTitulo());
       holder.tvSubtitulo.setText(publicaciones.get(position).getSubtitulo());
        Picasso.get()
                .load(publicaciones.get(position).getUrlImg())
                .into(holder.imageView);


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
                                System.out.println("Editar------------------------");
                                break;
                            case R.id.itemEliminar:
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

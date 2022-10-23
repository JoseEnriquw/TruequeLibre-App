package com.example.truequelibre;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAgregarImagenPublicacion extends RecyclerView.Adapter<AdapterAgregarImagenPublicacion.ViewHolderAgregarImagenPublicacion> {

    private ArrayList<Uri> uriArrayList;
    private Context context;
    public AdapterAgregarImagenPublicacion(ArrayList<Uri> uriArrayList, Context context) {
        this.context = context;
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public AdapterAgregarImagenPublicacion.ViewHolderAgregarImagenPublicacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_single_image,parent,false);
        return new ViewHolderAgregarImagenPublicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAgregarImagenPublicacion.ViewHolderAgregarImagenPublicacion holder, int position) {
        holder.imgView.setImageURI(uriArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public class ViewHolderAgregarImagenPublicacion extends RecyclerView.ViewHolder {

        ImageView imgView;

        public ViewHolderAgregarImagenPublicacion(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgvAgregarPublicaciones);
        }
    }
}

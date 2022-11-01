package com.example.truequelibre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterCategorias extends BaseAdapter {

    private Context context;
    private List<ECategorias> articulos ;


    public AdapterCategorias(Context context, List<ECategorias> arts) {

        this.context = context;
        articulos = arts;
    }

    @Override
    public int getCount() {

        return articulos != null ? articulos.size() : 0;
    }

    @Override
    public ECategorias getItem(int position) {
        return articulos != null ?  articulos.get(position): null;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getIds();

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view== null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.grid_item_categorias, viewGroup, false);

        }


        TextView Titulo = ((TextView) view.findViewById(R.id.idcategoriastitulo));
        ImageView Img = (ImageView) view.findViewById(R.id.idimgcategorias);

        if(articulos != null){
            ECategorias item = (ECategorias) getItem(position);

            Titulo.setText( item.getTitulo());

            Picasso.get()
                    .load(item.getUrlImg())
                    .into(Img);

        }



        return view; }


}

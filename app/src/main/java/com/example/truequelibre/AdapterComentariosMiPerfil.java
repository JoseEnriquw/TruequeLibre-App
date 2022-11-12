package com.example.truequelibre;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.util.List;

public class AdapterComentariosMiPerfil extends RecyclerView.Adapter <AdapterComentariosMiPerfil.ViewHolderComentarios>{

    private Context context;
    private List<CalificacionUsuario> calificacion;

    public AdapterComentariosMiPerfil(Context context, List<CalificacionUsuario> calificacion) {
        this.context = context;
        this.calificacion = calificacion;
    }

    public  static  class ViewHolderComentarios extends RecyclerView.ViewHolder
    {
        ImageView imageViewComentarioMiPerfil;
        TextView tvComentarioMiPerfil;
        TextView  tvNombreApellidoComentarioMiPerfil;
        RatingBar ratingBarComentarioMiPerfil;
        TextView fechaComentarioMiPerfil;

        public ViewHolderComentarios(@NonNull View itemView) {
            super(itemView);

            imageViewComentarioMiPerfil= itemView.findViewById(R.id.ivFotoPerfilComentarios);
            tvComentarioMiPerfil=itemView.findViewById(R.id.tvComentariosMiPerfil);
            tvNombreApellidoComentarioMiPerfil=itemView.findViewById(R.id.tvNombreApellidoComentariosMiPerfil);
            ratingBarComentarioMiPerfil = itemView.findViewById(R.id.rbValoracionComentarioPerfil);
            fechaComentarioMiPerfil= itemView.findViewById(R.id.tvTiempoComentario);

        }
    }

    @NonNull
    @Override
    public AdapterComentariosMiPerfil.ViewHolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_comentarios_mi_perfil,parent,false);
        return new AdapterComentariosMiPerfil.ViewHolderComentarios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentariosMiPerfil.ViewHolderComentarios holder, int position) {
        holder.ratingBarComentarioMiPerfil.setIsIndicator(true);
        holder.ratingBarComentarioMiPerfil.setRating(calificacion.get(position).getEstrellas());
        holder.tvComentarioMiPerfil.setText(calificacion.get(position).getComentario());
        holder.tvNombreApellidoComentarioMiPerfil.setText(calificacion.get(position).getNombreApellido()==null?
                "Desconocido":calificacion.get(position).getNombreApellido());

        if (calificacion.get(position).getImagen() != null){
            byte[] byteArray =  Base64.decode(calificacion.get(position).getImagen(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageViewComentarioMiPerfil.setImageBitmap(theImage);
        }

        holder.fechaComentarioMiPerfil.setText(String.valueOf(calificacion.get(position).getFecha()));
    }

    @Override
    public int getItemCount() {
        return calificacion.size();
    }
}

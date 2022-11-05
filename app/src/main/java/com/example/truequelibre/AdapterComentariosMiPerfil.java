package com.example.truequelibre;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterComentariosMiPerfil extends RecyclerView.Adapter <AdapterComentariosMiPerfil.ViewHolderComentarios>{

    private Context context;
    private List<ECalificacionUsuario> calificacion;

    public AdapterComentariosMiPerfil(Context context, List<ECalificacionUsuario> calificacion) {
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
        //holder.ratingBarComentarioMiPerfil.setText(calificacion.get(position).getRatingBar());
        holder.tvComentarioMiPerfil.setText(calificacion.get(position).getComentarioMiPerfil());
        holder.tvNombreApellidoComentarioMiPerfil.setText(calificacion.get(position).geteUsuario().getDNI().getNombre());
        //holder.fechaComentarioMiPerfil.setText((CharSequence) calificacion.get(position).getFechaComentario());
        Picasso.get()
                .load(calificacion.get(position).geteUsuario().getUrlImg())
                .into(holder.imageViewComentarioMiPerfil);

    }

    @Override
    public int getItemCount() {
        return calificacion.size();
    }



}

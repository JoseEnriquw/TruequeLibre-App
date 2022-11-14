package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.UpdateFinalizarVM;
import com.example.truequelibre.Entity.UpdateOfertaVM;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IOfertaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterNotificaciones extends RecyclerView.Adapter <AdapterNotificaciones.ViewHolderNotificaciones>{

    private Context context;
    private List<OfertasResponse> listaChat;
    private String nombreUsuario;
    AlertDialog.Builder builder;
    IOfertaService service;

    private Integer positionaux;
    private Integer id_usuario_principal;
    private Integer id_usuario_logeado;
    private Integer id_usuario_ofertante;

    public AdapterNotificaciones(Context context, List<OfertasResponse> listaOfertas, String nombreUsuario, Integer id_usuario_logeado) {
        this.context = context;
        this.listaChat = listaOfertas;
        this.nombreUsuario = nombreUsuario;
        this.id_usuario_logeado =id_usuario_logeado;
    }

    @SuppressLint("RestrictedApi")
    public  static  class ViewHolderNotificaciones extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvNombreyapellido;
        TextView tvdescripcionulo;
        TextView tvfechanotificacion;
        ImageButton btnOpciones;
        MenuBuilder menuBuilder;
        MenuInflater menuInflater;
        FloatingActionButton campanita;


        public ViewHolderNotificaciones(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.chatfotoarticulo);
            tvNombreyapellido=itemView.findViewById(R.id.tvUsuarioChat);
            tvdescripcionulo=itemView.findViewById(R.id.tvMensajeChat);
         //   tvfechanotificacion=itemView.findViewById(R.id.aceptadosfecha);
            btnOpciones= (ImageButton) itemView.findViewById(R.id.chatibMoreOptions);
            menuBuilder=new MenuBuilder(itemView.getContext());
            menuInflater=new MenuInflater(itemView.getContext());
            campanita =itemView.findViewById(R.id.notiFinalizartrueque);
        }
    }

    @NonNull
    @Override
    public AdapterNotificaciones.ViewHolderNotificaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gir_item_notificaciones,parent,false);
        builder = new AlertDialog.Builder(context);
        service= Apis.getOfertaService();
        return new AdapterNotificaciones.ViewHolderNotificaciones(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterNotificaciones.ViewHolderNotificaciones holder, @SuppressLint("RecyclerView") int position) {

        positionaux =position;
        holder.tvNombreyapellido.setText(listaChat.get(position).getNombre_ofertante());
        holder.tvdescripcionulo.setText(listaChat.get(position).getNombre_ofertante());
        id_usuario_principal =listaChat.get(position).getId_usuario_principal();
        id_usuario_ofertante=listaChat.get(position).getId_usuario_ofertante();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Chat.class);
                intent.putExtra("idOferta", listaChat.get(position).getId());
                intent.putExtra("usuarioActual", nombreUsuario);
                context.startActivity(intent);
            }
        });

        if(listaChat.get(position).getEstado_id()==8){
            holder.campanita.setVisibility(View.VISIBLE);
            holder.btnOpciones.setVisibility(View.INVISIBLE);
                if((id_usuario_logeado ==id_usuario_principal && listaChat.get(position).isUsuario_principal_acepto()==true)|| (id_usuario_logeado !=id_usuario_principal && listaChat.get(position).isUsuario_ofertante_acepto()==true) ){
                    holder.campanita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                              //ESPERANDO QUE EL OTRO USUARIO REPSONDA
                            EsperandoConfirmacion();
                        }
                    });
                }else {

                    holder.campanita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ACEPTAR LA FINALIZACION?
                            AceptarFinalizacion();                        }
                    });
                }
                if(listaChat.get(position).isUsuario_principal_acepto()==true && listaChat.get(position).isUsuario_ofertante_acepto()==true){
                    int miColor = context.getResources().getColor(R.color.mi_color_verde);
                    ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{miColor});
                    holder.campanita.setBackgroundTintList(csl);
                    holder.campanita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Calificar

                            CalificarUsuarioMensaje();
                            notifyDataSetChanged();

                        }
                    });
                }

        }else{
            holder.campanita.setVisibility(View.INVISIBLE);

        }

        holder.menuInflater.inflate(R.menu.popup_menu_chat,holder.menuBuilder);
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
                            case R.id.itemfinalizar:
                                positionaux =position;
                                FinalizarMensaje();
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
        return listaChat.size();
    }

    private void FinalizarMensaje(){
        builder.setMessage("Estás seguro que deseas dar por finalizado el trueque? (Se le avisara al otro usuario para que acepte)")
                .setCancelable(false)
                .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateEstadoFinalizarTrueque();
                        updateFinalizarTrueque(false);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(context,"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Finalizar Trueque");
        alert.show();
    }

    private void CalificarUsuarioMensaje(){
        builder.setMessage("Desea dejarle una calificación al usuario ?")
                .setCancelable(false)
                .setPositiveButton("Calificar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ABRIR CALIFICAR USUARIO
                        Intent i = new Intent(context,CalificarUsuario.class);

                        if(id_usuario_logeado==id_usuario_principal){
                        i.putExtra("idusuariocalificador",id_usuario_logeado);
                        i.putExtra("idcalificado",id_usuario_ofertante);}
                        else{
                            i.putExtra("idusuariocalificador",id_usuario_ofertante);
                            i.putExtra("idcalificado",id_usuario_logeado);
                        }
                        i.putExtra("idoferta", listaChat.get(positionaux).getId());
                        context.startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(context,"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Calificar Usuario");
        alert.show();
    }

    private void updateEstadoFinalizarTrueque(){
        UpdateOfertaVM estado = new UpdateOfertaVM(8);
        Call<ResponseBody> updateRequest = service.updateOferta(listaChat.get(positionaux).getId(),estado);
        updateRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {

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

    public void updateFinalizarTrueque(boolean cerrado){
        UpdateFinalizarVM request =new UpdateFinalizarVM();
        if(cerrado ==true){
            request.setUsuario_principal_acepto(true);
            request.setUsuario_ofertante_acepto(true);
        }else if(id_usuario_logeado ==id_usuario_principal ){
            request.setUsuario_principal_acepto(true);
        } else{request.setUsuario_ofertante_acepto(true); }

        Call<ResponseBody> updateRequest = service.updatefinalizartrueque(listaChat.get(positionaux).getId(),request);
        updateRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
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

    public void EsperandoConfirmacion(){
        builder.setMessage("   ")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Esperando confirmación del otro usuario");
        alert.show();
    }

    public void AceptarFinalizacion(){
        builder.setMessage("")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateFinalizarTrueque(true);
                    }
                })
                .setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(context,"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Se ha propuesto la finalización del trueque acepta?");
        alert.show();
    }
}
package com.example.truequelibre;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequelibre.Entity.Mensaje;
import com.example.truequelibre.Entity.Oferta;
import com.example.truequelibre.Entity.PublicacionResponseNotificacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IOfertaService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity {

    private RecyclerView rvMensajes;
    private TextView tvNombreUsuario;
    private EditText txtMensaje;
    private Button btnEnviar;
    private ImageView imgv;
    private AdapterMensajes adapterMensajes;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private Integer idOferta;
    private String usOfertante;
    private String usActual;
    private Integer idUsuarioActual;
    private String fotoUsuarioActual;
    private IOfertaService service;
    private Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        context = this;

        idOferta=getIntent().getIntExtra("idOferta",0);


        usActual =getIntent().getStringExtra("usuarioActual");
        idUsuarioActual = getIntent().getIntExtra("idUsuarioActual",0);


        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuarioChat);
        txtMensaje = (EditText) findViewById(R.id.txtEnviarMensajeChat);
        btnEnviar = (Button) findViewById(R.id.btnEnviarMensajeChat);
        imgv = (ImageView) findViewById(R.id.imgvUsuarioChat);

        service = Apis.getOfertaService();
        Call<PublicacionResponseNotificacion> callOferta = service.getOferta(idOferta);

        callOferta.enqueue(new Callback<PublicacionResponseNotificacion>() {
            @Override
            public void onResponse(Call<PublicacionResponseNotificacion> call, Response<PublicacionResponseNotificacion> response) {
                if (response.isSuccessful()){
                    PublicacionResponseNotificacion body = response.body();
                    if (body.getId_usuario_ofertante().equals(idUsuarioActual)){
                        tvNombreUsuario.setText(body.getNombre_usuario_principal());
                        fotoUsuarioActual = body.getImagen_usuario_ofertante();
                        imgv.setImageBitmap(ImagenConverter.convertByteToBitmap(body.getImagen_usuario_principal()));
                    }
                    else{
                        tvNombreUsuario.setText(body.getNombre_usuario_ofertante());
                        fotoUsuarioActual = body.getImagen_usuario_principal();
                        imgv.setImageBitmap(ImagenConverter.convertByteToBitmap(body.getImagen_usuario_ofertante()));
                    }

                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference("chat-" + body.getId_usuario_ofertante() + "-" +  body.getId_usuario_principal());

                    adapterMensajes = new AdapterMensajes(context,idUsuarioActual);
                    LinearLayoutManager l = new LinearLayoutManager(context);
                    rvMensajes.setLayoutManager(l);
                    rvMensajes.setAdapter(adapterMensajes);

                    btnEnviar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!txtMensaje.getText().toString().equals("")){
                                databaseReference.push().setValue(new Mensaje(txtMensaje.getText().toString(), idUsuarioActual,fotoUsuarioActual));
                                txtMensaje.setText("");
                            }

                        }
                    });

                    adapterMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onItemRangeInserted(int positionStart, int itemCount) {
                            super.onItemRangeInserted(positionStart, itemCount);
                            setScrollBar();
                        }
                    });

                    databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Mensaje m = snapshot.getValue(Mensaje.class);
                            adapterMensajes.addMensaje(m);
                        }
                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {            }
                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {            }
                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {           }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {           }
                    });
                }else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() { }.getType();

                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PublicacionResponseNotificacion> call, Throwable t) {

            }
        });







    }

    private void setScrollBar(){
        rvMensajes.scrollToPosition(adapterMensajes.getItemCount()+1);
    }
}
package com.example.truequelibre;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.ICalificacionUsuariosService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiPerfil extends Fragment {

    private RecyclerView _recyclerView;
    private AdapterComentariosMiPerfil _adapter;
    Usuario usuario;
    TextView tvNombreApellido;
    ImageView ivFoto;
    List<CalificacionUsuario> lista = new ArrayList<>();
    ICalificacionUsuariosService service;
    RatingBar ratingBar;
    Button btnSalir;
    private ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static MiPerfil newInstance(String param1, String param2) {
        MiPerfil fragment = new MiPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        MainActivity activity =(MainActivity) getActivity();
        usuario= activity.getUsuario();

        progressBar = (ProgressBar) view.findViewById(R.id.pbMiPerfil);


        btnSalir = (Button) view.findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Login.class);
                v.getContext().startActivity(intent);
            }
        });

        await(false);

        service= Apis.getCalificacionUsuariosService();

        tvNombreApellido= (TextView)view.findViewById(R.id.tvNombreApellidoPerfil);
        ivFoto=(ImageView)view.findViewById(R.id.ivFotoPerfil);
        ratingBar=(RatingBar)view.findViewById(R.id.rbMiPerfil);
        ratingBar.setIsIndicator(true);

        tvNombreApellido.setText(usuario.getNombreApellido());
        if (usuario.getImagen() != null){
            byte[] byteArray =  Base64.decode(usuario.getImagen(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            ivFoto.setImageBitmap(theImage);
        }


        _recyclerView =(RecyclerView) view.findViewById(R.id.rvComentariosMiPerfil);

        Call<List<CalificacionUsuario>> call =service.getCalificacionesByUsuario(usuario.getId());

        call.enqueue(new Callback<List<CalificacionUsuario>>() {
            @Override
            public void onResponse(Call<List<CalificacionUsuario>> call, retrofit2.Response<List<CalificacionUsuario>> response) {
                await(true);
                if(response.isSuccessful()) {
                    lista = response.body();
                    _adapter= new AdapterComentariosMiPerfil(getContext(),lista);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);

                    float promedio=0;
                    for (CalificacionUsuario item: lista)
                    {
                        promedio+=item.getEstrellas();
                    }
                    promedio/=lista.size();

                    ratingBar.setRating(promedio);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() { }.getType();

                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CalificacionUsuario>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void await(boolean enabled){
        progressBar.setVisibility(enabled ? View.GONE : View.VISIBLE);
        btnSalir.setEnabled(enabled);
    }
}
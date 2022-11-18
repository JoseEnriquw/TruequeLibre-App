package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Publicaciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Publicaciones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView _recyclerView;
    private AdapterPublicaciones _adapter;
    IPublicacionService service;
    private List<Publicacion> lista = new ArrayList<>();
    Usuario usuario;
    private ProgressBar progressBar;
    private Button btnAgregarPublicacion;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Publicaciones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Publicaciones.
     */
    // TODO: Rename and change types and number of parameters
    public static Publicaciones newInstance(String param1, String param2) {
        Publicaciones fragment = new Publicaciones();
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

    @SuppressLint({"MissingInflatedId", "RestrictedApi"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_publicaciones, container, false);

        MainActivity activity =(MainActivity) getActivity();
        usuario= activity.getUsuario();

        btnAgregarPublicacion = (Button) view.findViewById(R.id.btnAgregarPublicacion);
        progressBar = (ProgressBar) view.findViewById(R.id.pbMisPublicaciones);
        await(false);


        //Cargar el RecyclerView
        _recyclerView =(RecyclerView) view.findViewById(R.id.rvPublicaciones);

        service= Apis.getPublicacionService();
        Call<List<Publicacion>> call =service.getPublicaciones(usuario.getId());

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                await(true);
               if(response.isSuccessful()) {

                   lista = response.body();
                   _adapter = new AdapterPublicaciones(getContext(), lista);

                   GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                   _recyclerView.setLayoutManager(gridLayoutManager);
                   _recyclerView.setHasFixedSize(true);
                   _recyclerView.setAdapter(_adapter);
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
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });



        //Onclick btn Agregar Publicacion
        Button btnAgregarPublicacion= (Button) view.findViewById(R.id.btnAgregarPublicacion);
        btnAgregarPublicacion.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(getActivity().getApplicationContext(),AgregarPublicaciones.class);
                        intent.putExtra("idUsuario", usuario.getId());
                        startActivity(intent);
                    }
                }
        );

        return view;
    }

    private void await(boolean enabled){
        progressBar.setVisibility(enabled ? View.GONE : View.VISIBLE);
        btnAgregarPublicacion.setEnabled(enabled);
    }
}
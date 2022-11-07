package com.example.truequelibre;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

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
    private  List<Publicacion> lista = new ArrayList<>();

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

        //Cargar el RecyclerView
        _recyclerView =(RecyclerView) view.findViewById(R.id.rvPublicaciones);


        /*Persona per = new Persona("34695008d","regina","laurentino");
        Estado Estado = new Estado();
        Usuario usu = new Usuario(01,"mail","regina@laurentino", Estado, per);
        Categoria cat = new Categoria();
        Condicion CONDI = new Condicion();

        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI));*/

        String url = "http://10.0.2.2:8080/api/v1/publicacion";

        new Thread(new Runnable() {
            public void run() {
                StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type publicacionListType = new TypeToken<ArrayList<Publicacion>>(){}.getType();
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            lista = mapper.readValue(response, lista.getClass());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }


                        //lista = new Gson().fromJson(response,publicacionListType);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Volley.newRequestQueue(view.getContext()).add(postRequest);
            }
        }).start();


        _adapter= new AdapterPublicaciones(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        //Onclick btn Agregar Publicacion
        Button btnAgregarPublicacion= (Button) view.findViewById(R.id.btnAgregarPublicacion);
        btnAgregarPublicacion.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(getActivity().getApplicationContext(),AgregarPublicaciones.class);
                        startActivity(intent);
                    }
                }
        );

        return view;
    }
}
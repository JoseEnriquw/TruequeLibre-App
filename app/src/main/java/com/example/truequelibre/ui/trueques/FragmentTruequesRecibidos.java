package com.example.truequelibre.ui.trueques;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.truequelibre.AdapterPublicaciones;
import com.example.truequelibre.AdapterRecibidos;
import com.example.truequelibre.Entity.FiltrarOfertaRequest;
import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.MainActivity;
import com.example.truequelibre.R;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IOfertaService;
import com.example.truequelibre.Utils.Notify;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTruequesRecibidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTruequesRecibidos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TITLE = "Recibidos";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView _recyclerView;
    private AdapterRecibidos _adapter;
    Usuario usuario;
    IOfertaService service;
    private List<OfertasResponse> lista = new ArrayList<>();
    FiltrarOfertaRequest ofertas;
    private ProgressBar progressBar;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTruequesRecibidos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTruequesRecibidos newInstance(String param1, String param2) {
        FragmentTruequesRecibidos fragment = new FragmentTruequesRecibidos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentTruequesRecibidos() {

    }

    public static FragmentTruequesRecibidos newInstance() {

        return new FragmentTruequesRecibidos();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trueques_recibidos, container, false);

        _recyclerView =(RecyclerView) view.findViewById(R.id.rvrecibidos);



        MainActivity activity =(MainActivity) getActivity();
        usuario= activity.getUsuario();
        FiltrarOfertaRequest requestofertas = new FiltrarOfertaRequest(usuario.getId(),  "Recibidos");

        progressBar = (ProgressBar) view.findViewById(R.id.pbTruequesRecibidos);
        progressBar.setVisibility(View.VISIBLE);


        service= Apis.getOfertaService();
        Call<List<OfertasResponse>> call =service.getAllOfertasRecibidas(requestofertas);

        call.enqueue(new Callback<List<OfertasResponse>>() {
            @Override
            public void onResponse(Call<List<OfertasResponse>> call, retrofit2.Response<List<OfertasResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful()) {
                    lista = response.body();
                    _adapter = new AdapterRecibidos(getContext(), lista,progressBar);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Notify>>() { }.getType();

                    List<Notify> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Notify item : message) {
                        Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OfertasResponse>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });

        _adapter= new AdapterRecibidos(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        return view;
    }
}
package com.example.truequelibre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.ICategoriaService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Buscar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView _recyclerView;
    private AdapterCategorias _adapter;
    List<Categoria> lista= new ArrayList<>();
    ICategoriaService service;
    private ProgressBar progressBar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buscar() {
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
    public static Buscar newInstance(String param1, String param2) {
        Buscar fragment = new Buscar();
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

        View view=inflater.inflate(R.layout.fragment_buscar, container, false);

        service= Apis.getCategoriaService();
        Call<List<Categoria>> call =service.getCategorias();
        MainActivity activity =(MainActivity) getActivity();

        progressBar = (ProgressBar) view.findViewById(R.id.pbBuscar);
        progressBar.setVisibility(View.VISIBLE);


        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, retrofit2.Response<List<Categoria>> response) {
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful()) {
                    lista = response.body();
                    _adapter= new AdapterCategorias(getContext(),lista);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {
                    }.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();

            }
        });

        _recyclerView =(RecyclerView) view.findViewById(R.id.rvCategorias);
        return view;
    }
}

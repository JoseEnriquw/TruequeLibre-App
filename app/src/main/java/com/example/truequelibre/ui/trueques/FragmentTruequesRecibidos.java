package com.example.truequelibre.ui.trueques;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truequelibre.AdapterRecibidos;
import com.example.truequelibre.Categoria;
import com.example.truequelibre.Condicion;
import com.example.truequelibre.Estado;
import com.example.truequelibre.Oferta;
import com.example.truequelibre.Persona;
import com.example.truequelibre.Publicacion;
import com.example.truequelibre.Usuario;
import com.example.truequelibre.R;

import java.util.ArrayList;
import java.util.List;

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

        List<Oferta> lista = new ArrayList<Oferta>();

        Persona per = new Persona("34695008d","regina","laurentino");
        Estado Estado = new Estado();
        Usuario usu = new Usuario(01,"mail","regina@laurentino", Estado, per);
        Categoria cat = new Categoria();
        Condicion CONDI = new Condicion();

        Publicacion publi = new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI);
        Publicacion ofert = new Publicacion(1,usu,"teclado","para escribir",cat,cat,null,CONDI);

        com.example.truequelibre.Estado estado = new Estado();

         lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));
        lista.add(new Oferta(estado,publi, ofert));


        _adapter= new AdapterRecibidos(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        return view;
    }
}
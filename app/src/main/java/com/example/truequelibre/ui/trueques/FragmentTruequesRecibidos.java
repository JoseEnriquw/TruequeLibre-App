package com.example.truequelibre.ui.trueques;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truequelibre.AdapterNotificaciones;
import com.example.truequelibre.AdapterRecibidos;
import com.example.truequelibre.EEstado;
import com.example.truequelibre.EOferta;
import com.example.truequelibre.EPersona;
import com.example.truequelibre.EPublicaciones;
import com.example.truequelibre.EUsuario;
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

        List<EOferta> lista = new ArrayList<EOferta>();

        EPersona per = new EPersona("34695008d","regina","laurentino");
        EUsuario usu = new EUsuario(01,per,"regina@laurentino", "zarasa", true,"https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg");
        EPublicaciones publi = new EPublicaciones("publicacion", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu);
        EPublicaciones ofert = new EPublicaciones("oferta", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu);

        EEstado estado = new EEstado();

         lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));
        lista.add(new EOferta(estado,publi, ofert));


        _adapter= new AdapterRecibidos(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        return view;
    }
}
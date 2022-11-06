package com.example.truequelibre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.Entity.ECategorias;

import java.util.ArrayList;
import java.util.List;

public class Buscar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView _recyclerView;
    private AdapterCategorias _adapter;

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

        _recyclerView =(RecyclerView) view.findViewById(R.id.rvCategorias);
        List<ECategorias> lista = new ArrayList<ECategorias>();
        lista.add(new ECategorias("Bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg"));
        lista.add(new ECategorias("Teclado",  "https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png"));
        lista.add(new ECategorias("Mouse",  "https://www.venex.com.ar/products_images/1582916326_m7191.png"));
        lista.add(new ECategorias("Auricular",  "https://www.fullh4rd.com.ar/img/productos/Pics_Prod/auriculares-logitech-g935-wireless-71-981000742-0.jpg"));
        _adapter= new AdapterCategorias(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        return view;
    }
}

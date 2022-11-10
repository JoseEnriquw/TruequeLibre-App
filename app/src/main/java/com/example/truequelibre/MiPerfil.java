package com.example.truequelibre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiPerfil extends Fragment {

    private RecyclerView _recyclerView;
    private AdapterComentariosMiPerfil _adapter;

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
        _recyclerView =(RecyclerView) view.findViewById(R.id.rvComentariosMiPerfil);
        List<CalificacionUsuario> lista = new ArrayList<CalificacionUsuario>();
        RatingBar ratingBar=new RatingBar(getContext());
        Date date = new Date(2022/11/05);


        _adapter= new AdapterComentariosMiPerfil(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        // Inflate the layout for this fragment
        return view;
    }
}
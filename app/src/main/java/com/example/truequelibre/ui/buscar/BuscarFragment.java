package com.example.truequelibre.ui.buscar;

import static android.system.Os.remove;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterCategorias;
import com.example.truequelibre.AdapterPublicaciones;
import com.example.truequelibre.AgregarPublicaciones;
import com.example.truequelibre.ECategorias;
import com.example.truequelibre.EPublicaciones;
import com.example.truequelibre.PublicacionesPorCategoria;
import com.example.truequelibre.R;
import com.example.truequelibre.databinding.FragmentBuscarBinding;

import java.util.ArrayList;
import java.util.List;


public class BuscarFragment extends Fragment {

    private FragmentBuscarBinding binding;
    private RecyclerView _recyclerView;
    private AdapterCategorias _adapter;
    private GridView gridView;
    Context contexto ;
    List<ECategorias> lista = new ArrayList<ECategorias>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuscarViewModel dashboardViewModel =
                new ViewModelProvider(this).get(BuscarViewModel.class);

        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        contexto = this.getContext();
        gridView = view.findViewById(R.id.rvCategorias);
        Activity activity = getActivity();
        final TextView textView = binding.textBuscar;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        lista.add(new ECategorias("Bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg"));
        lista.add(new ECategorias("Teclado",  "https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png"));
        lista.add(new ECategorias("Mouse",  "https://www.venex.com.ar/products_images/1582916326_m7191.png"));
        lista.add(new ECategorias("Auricular",  "https://www.fullh4rd.com.ar/img/productos/Pics_Prod/auriculares-logitech-g935-wireless-71-981000742-0.jpg"));

       return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.rvCategorias);
        _adapter= new AdapterCategorias(getContext(),lista);
        gridView.setAdapter((ListAdapter) _adapter);

      /*  ImageView Img = (ImageView) view.findViewById(R.id.idimgcategorias);
        Img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                PublicacionesPorCategoria fragment1 = new PublicacionesPorCategoria();
                manager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                         .add(R.id.fragmentbuscar, fragment1)
                        .addToBackStack("true")
                        .commit();
            }
            }
        );*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.truequelibre.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterPublicaciones;
import com.example.truequelibre.EPublicaciones;
import com.example.truequelibre.R;
import com.example.truequelibre.databinding.FragmentPublicacionesBinding;
import java.util.ArrayList;
import java.util.List;


public class PublicacionesFragment extends Fragment {

    private FragmentPublicacionesBinding binding;
    private RecyclerView _recyclerView;
    private AdapterPublicaciones _adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PublicacionesViewModel notificationsViewModel =
                new ViewModelProvider(this).get(PublicacionesViewModel.class);

        binding = FragmentPublicacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtPublicaciones;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        _recyclerView =getActivity().findViewById(R.id.rvPublicaciones);
        List<EPublicaciones> lista = new ArrayList<EPublicaciones>();
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg"));
        lista.add(new EPublicaciones("Teclado", "Alto teclado hp", "https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png"));
        lista.add(new EPublicaciones("Mouse", "Alto mouse Redragon", "https://www.venex.com.ar/products_images/1582916326_m7191.png"));
        lista.add(new EPublicaciones("Auricular", "Altos Auriculares", "https://www.fullh4rd.com.ar/img/productos/Pics_Prod/auriculares-logitech-g935-wireless-71-981000742-0.jpg"));
        _adapter= new AdapterPublicaciones(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
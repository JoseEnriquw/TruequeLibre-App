package com.example.truequelibre.ui.notifications;

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

import com.example.truequelibre.AdapterArticulos;
import com.example.truequelibre.AdapterCategorias;
import com.example.truequelibre.AdapterNotificaciones;
import com.example.truequelibre.EPersona;
import com.example.truequelibre.EPublicaciones;
import com.example.truequelibre.EUsuario;
import com.example.truequelibre.R;
import com.example.truequelibre.databinding.FragmentNotificationesBinding;

import java.util.ArrayList;
import java.util.List;


public class NotificationesFragment extends Fragment {

    private FragmentNotificationesBinding binding;
    private RecyclerView _recyclerView;
    private AdapterNotificaciones _adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationesViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationesViewModel.class);

        binding = FragmentNotificationesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _recyclerView =(RecyclerView) root.findViewById(R.id.rvnotificaciones);

        List<EPublicaciones> lista = new ArrayList<EPublicaciones>();

        EPersona per = new EPersona("34695008d","regina","laurentino");
        EUsuario usu = new EUsuario(01,per,"regina@laurentino", "zarasa", true,"https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg");

        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));

        _adapter= new AdapterNotificaciones(getContext(),lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
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
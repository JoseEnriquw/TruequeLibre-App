package com.example.truequelibre.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterNotificaciones;
import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Entity.Condicion;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
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

        _recyclerView =(RecyclerView) root.findViewById(R.id.rvNotificaciones);

        List<Publicacion> lista = new ArrayList<Publicacion>();

        Persona per = new Persona("34695008d","regina","laurentino");
        Estado estado = new Estado();
        Categoria cat = new Categoria();
        Condicion CONDI = new Condicion();

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
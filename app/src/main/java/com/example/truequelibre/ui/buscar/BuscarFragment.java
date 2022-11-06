package com.example.truequelibre.ui.buscar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterCategorias;
import com.example.truequelibre.Categoria;
import com.example.truequelibre.databinding.FragmentBuscarBinding;

import java.util.ArrayList;
import java.util.List;


public class BuscarFragment extends Fragment {

    private FragmentBuscarBinding binding;
    private RecyclerView _recyclerView;
    private AdapterCategorias _adapter;
    private GridView gridView;
    Context contexto ;
    List<Categoria> lista = new ArrayList<Categoria>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuscarViewModel dashboardViewModel =
                new ViewModelProvider(this).get(BuscarViewModel.class);

        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textBuscar;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

       return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.truequelibre.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterPublicaciones;
import com.example.truequelibre.databinding.FragmentPublicacionesBinding;


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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.truequelibre.ui.miPerfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.truequelibre.databinding.FragmentMiPerfilBinding;


public class MiPerfilFragment extends Fragment {

    private FragmentMiPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MiPerfilViewModel homeViewModel =
                new ViewModelProvider(this).get(MiPerfilViewModel.class);

        binding = FragmentMiPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


      return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
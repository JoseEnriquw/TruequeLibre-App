package com.example.truequelibre.ui.trueques;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.truequelibre.databinding.FragmentTruequesBinding;


public class TruequesFragment extends Fragment {

    private FragmentTruequesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TruequesViewModel truequesViewModel =
                new ViewModelProvider(this).get(TruequesViewModel.class);

        binding = FragmentTruequesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTrueques;
        truequesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.truequelibre.ui.trueques;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truequelibre.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTruequesAceptados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTruequesAceptados extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TITLE = "Aceptados";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTruequesAceptados() {
        // Required empty public constructor
    }

    public static FragmentTruequesAceptados newInstance() {

        return new FragmentTruequesAceptados();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTruequesAceptados.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTruequesAceptados newInstance(String param1, String param2) {
        FragmentTruequesAceptados fragment = new FragmentTruequesAceptados();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trueques_aceptados, container, false);
    }
}
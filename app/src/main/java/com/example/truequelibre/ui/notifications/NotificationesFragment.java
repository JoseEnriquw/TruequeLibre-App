package com.example.truequelibre.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequelibre.AdapterAceptados;
import com.example.truequelibre.AdapterNotificaciones;
import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Entity.Condicion;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.FiltrarOfertaRequest;
import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.MainActivity;
import com.example.truequelibre.R;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IOfertaService;
import com.example.truequelibre.databinding.FragmentNotificationesBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class NotificationesFragment extends Fragment {

    private FragmentNotificationesBinding binding;
    private RecyclerView _recyclerView;
    private AdapterNotificaciones _adapter;
    IOfertaService service;
    Usuario usuario;
    List<OfertasResponse> lista = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationesViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationesViewModel.class);

        binding = FragmentNotificationesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainActivity activity =(MainActivity) getActivity();
        usuario= activity.getUsuario();

        _recyclerView =(RecyclerView) root.findViewById(R.id.rvNotificaciones);


        FiltrarOfertaRequest requestofertas = new FiltrarOfertaRequest(usuario.getId(),  "Aceptados");


        service= Apis.getOfertaService();
        Call<List<OfertasResponse>> call =service.getAllOfertasRecibidas(requestofertas);

        call.enqueue(new Callback<List<OfertasResponse>>() {
            @Override
            public void onResponse(Call<List<OfertasResponse>> call, retrofit2.Response<List<OfertasResponse>> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    _adapter = new AdapterNotificaciones(getContext(), lista,usuario.getNombreApellido(), usuario.getId());

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() { }.getType();

                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OfertasResponse>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package hn.uth.proyectofinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import hn.uth.proyectofinal.Entities.Lugar;
import hn.uth.proyectofinal.OnItemClickListener;
import hn.uth.proyectofinal.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements OnItemClickListener<Lugar> {
    private LugarAdapter adaptador;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        List<Lugar> lugarList = new ArrayList<>();
        adaptador = new LugarAdapter(lugarList,this);

        //
        homeViewModel.getAllLugares().observe(getViewLifecycleOwner(), lugares -> {
            if(lugares.isEmpty()){
                Snackbar.make(binding.RVLugares,"No hay lugares creados", Snackbar.LENGTH_LONG).show();
            }else{
                adaptador.setItems(lugares);
            }
        });

        //



        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.RVLugares.setLayoutManager(linearLayoutManager);
        binding.RVLugares.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClickt(Lugar data) {

    }
}
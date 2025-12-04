package com.example.appgestiontareas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.adapters.ActividadAdapter;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.repository.ActividadRepository;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ActividadRepository actividadRepository;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rvCards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        actividadRepository = new ActividadRepository(requireContext());

        // Usamos el Repository con callback
        actividadRepository.getAll(lista -> requireActivity().runOnUiThread(() -> {
            ActividadAdapter adapter = new ActividadAdapter(lista);
            recyclerView.setAdapter(adapter);
        }));
    }
}

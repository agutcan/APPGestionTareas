package com.example.appgestiontareas.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.activities.SettingsActivity;
import com.example.appgestiontareas.ui.adapters.ActividadAdapter;
import com.example.appgestiontareas.ui.database.repository.ActividadRepository;

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

//        ASI SE CAMBIA DE FRAGMENT        //

        Button btnCambiar = view.findViewById(R.id.btnCambiarFragment);
        btnCambiar.setOnClickListener(v -> {
            Fragment nuevoFragment = new ActivityFragment();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, nuevoFragment)
                    .addToBackStack(null)
                    .commit();
        });

        ImageButton btnAjustes = view.findViewById(R.id.ajustesBtn);
        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });
    }
}

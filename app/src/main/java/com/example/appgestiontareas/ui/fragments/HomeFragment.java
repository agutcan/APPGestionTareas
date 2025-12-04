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
import com.example.appgestiontareas.ui.adapters.ActividadCardAdapter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

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
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rvCards);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<String> tarjetas = new ArrayList<>();
        tarjetas.add("Actividad 1");
        tarjetas.add("Actividad 2");
        tarjetas.add("Actividad 3");
        tarjetas.add("Actividad 4");
        tarjetas.add("Actividad 5");
        tarjetas.add("Actividad 6");
        tarjetas.add("Actividad 7");
        tarjetas.add("Actividad 8");
        tarjetas.add("Actividad 9");
        tarjetas.add("Actividad 10");

        ActividadCardAdapter adapter = new ActividadCardAdapter(tarjetas);
        rv.setAdapter(adapter);
    }
}


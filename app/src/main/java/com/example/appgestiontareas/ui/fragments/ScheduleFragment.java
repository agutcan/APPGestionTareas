package com.example.appgestiontareas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.HorarioAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleFragment extends Fragment {

    Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes;
    RecyclerView recyclerAsignaturas;

    HashMap<String, ArrayList<String>> horario = new HashMap<>();
    HorarioAdapter adapter;

    public ScheduleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnLunes = view.findViewById(R.id.btnLunes);
        btnMartes = view.findViewById(R.id.btnMartes);
        btnMiercoles = view.findViewById(R.id.btnMiercoles);
        btnJueves = view.findViewById(R.id.btnJueves);
        btnViernes = view.findViewById(R.id.btnViernes);

        recyclerAsignaturas = view.findViewById(R.id.recyclerAsignaturas);
        recyclerAsignaturas.setLayoutManager(new LinearLayoutManager(requireContext()));
        cargarDatosHorario();

        adapter = new HorarioAdapter(new ArrayList<>(horario.get("Lunes")));
        recyclerAsignaturas.setAdapter(adapter);

        btnLunes.setOnClickListener(v -> cargarDia("Lunes"));
        btnMartes.setOnClickListener(v -> cargarDia("Martes"));
        btnMiercoles.setOnClickListener(v -> cargarDia("Miércoles"));
        btnJueves.setOnClickListener(v -> cargarDia("Jueves"));
        btnViernes.setOnClickListener(v -> cargarDia("Viernes"));


    }

    private void cargarDatosHorario() {
        horario.put("Lunes", new ArrayList<String>() {{
            add("Lengua");
            add("Matemáticas");
            add("Conocimiento del Medio");
            add("Inglés");
            add("Refuerzo/Lectura");
        }});

        horario.put("Martes", new ArrayList<String>() {{
            add("Lengua");
            add("Matemáticas");
            add("Francés");
            add("Educación Física");
            add("Refuerzo/Lectura");
        }});

        horario.put("Miércoles", new ArrayList<String>() {{
            add("Inglés");
            add("Lengua");
            add("Música");
            add("Matemáticas");
            add("Conocimiento del Medio");
        }});

        horario.put("Jueves", new ArrayList<String>() {{
            add("Matemáticas");
            add("Lengua");
            add("Educación Física");
            add("Inglés");
            add("Francés");
        }});

        horario.put("Viernes", new ArrayList<String>() {{
            add("Religión");
            add("Lengua");
            add("Matemáticas");
            add("Refuerzo/Lectura");
            add("Conocimiento del Medio");
        }});
    }

    private void cargarDia(String dia) {
        adapter.updateData(horario.get(dia));
    }




}

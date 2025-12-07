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
import com.example.appgestiontareas.ui.adapters.HorarioAdapter;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerAsignaturas;
    private HorarioAdapter adapter;

    private HashMap<Integer, String> mapaProfesores = new HashMap<>();
    private HashMap<String, List<Asignatura>> horario = new HashMap<>();
    private List<Usuario> listaProfesores;

    private Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes;



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

        recyclerAsignaturas = view.findViewById(R.id.recyclerAsignaturas);
        recyclerAsignaturas.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new HorarioAdapter(new ArrayList<>(), mapaProfesores);
        recyclerAsignaturas.setAdapter(adapter);


        btnLunes = view.findViewById(R.id.btnLunes);
        btnMartes = view.findViewById(R.id.btnMartes);
        btnMiercoles = view.findViewById(R.id.btnMiercoles);
        btnJueves = view.findViewById(R.id.btnJueves);
        btnViernes = view.findViewById(R.id.btnViernes);

        cargarProfesores();
        cargarDatosHorario();

        btnLunes.setOnClickListener(v -> cargarDia("Lunes"));
        btnMartes.setOnClickListener(v -> cargarDia("Martes"));
        btnMiercoles.setOnClickListener(v -> cargarDia("Miércoles"));
        btnJueves.setOnClickListener(v -> cargarDia("Jueves"));
        btnViernes.setOnClickListener(v -> cargarDia("Viernes"));

        ImageButton btnAjustes = view.findViewById(R.id.ajustesBtn2);
        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });


    }

    private void cargarProfesores() {
        new Thread(() -> {

            listaProfesores = AppDatabase.getInstance(requireContext())
                    .usuarioDao().getByRol("profesor");

            mapaProfesores.clear();

            for (Usuario p : listaProfesores) {
                mapaProfesores.put(p.getId(), p.getNombre());
            }

            cargarDatosHorario();

            requireActivity().runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                cargarDia("Lunes");
            });

        }).start();
    }

    private void cargarDatosHorario() {

        if (listaProfesores == null || listaProfesores.size() < 5) {
            return;
        }

        // Obtener IDs reales
        int id1 = listaProfesores.get(0).getId();
        int id2 = listaProfesores.get(1).getId();
        int id3 = listaProfesores.get(2).getId();
        int id4 = listaProfesores.get(3).getId();
        int id5 = listaProfesores.get(4).getId();
        int id6 = listaProfesores.get(5).getId();
        int id7 = listaProfesores.get(6).getId();
        int id8 = listaProfesores.get(7).getId();
        int id9 = listaProfesores.get(8).getId();


        horario.put("Lunes", new ArrayList<Asignatura>() {{
            add(new Asignatura(1, "Lengua", 3600, id1));
            add(new Asignatura(1, "Matemáticas", 3600, id2));
            add(new Asignatura(1, "Conocimiento del Medio", 3600, id3));
            add(new Asignatura(1, "Inglés", 3600, id7));
            add(new Asignatura(1, "Refuerzo", 3600, id9));
        }});

        horario.put("Martes", new ArrayList<Asignatura>() {{
            add(new Asignatura(1, "Lengua", 3600, id1));
            add(new Asignatura(1, "Matemáticas", 3600, id2));
            add(new Asignatura(1, "Francés", 3600, id8));
            add(new Asignatura(1, "Educación Física", 3600, id5 ));
            add(new Asignatura(1, "Refuerzo", 3600, id9));
        }});

        horario.put("Miércoles", new ArrayList<Asignatura>() {{
            add(new Asignatura(1, "Inglés", 3600, id7));
            add(new Asignatura(1, "Lengua", 3600, id1));
            add(new Asignatura(1, "Música", 3600, id6));
            add(new Asignatura(1, "Matemáticas", 3600, id2));
            add(new Asignatura(1, "Conocimiento del Medio", 3600, id3));
        }});

        horario.put("Jueves", new ArrayList<Asignatura>() {{
            add(new Asignatura(1, "Matemáticas", 3600, id2));
            add(new Asignatura(1, "Lengua", 3600, id1));
            add(new Asignatura(1, "Educación Física", 3600, id5 ));
            add(new Asignatura(1, "Inglés", 3600, id7 ));
            add(new Asignatura(1, "Francés", 3600, id8));
        }});

        horario.put("Viernes", new ArrayList<Asignatura>() {{
            add(new Asignatura(1, "Religión", 3600, id4));
            add(new Asignatura(1, "Lengua", 3600, id1));
            add(new Asignatura(1, "Matemáticas", 3600, id2));
            add(new Asignatura(1, "Refuerzo", 3600, id9));
            add(new Asignatura(1, "Conocimiento del Medio", 3600, id3));
        }});
    }

    private void cargarDia(String dia) {
        List<Asignatura> listaDelDia = horario.get(dia);
        if (listaDelDia != null) {
            adapter.updateData(listaDelDia);
        }
    }




}

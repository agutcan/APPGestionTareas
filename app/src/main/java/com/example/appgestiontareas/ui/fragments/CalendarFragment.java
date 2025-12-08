package com.example.appgestiontareas.ui.fragments;

import static com.example.appgestiontareas.ui.utils.utils.convertirFecha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.activities.SettingsActivity;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.decorators.DayMultiColorCircularDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalendarFragment extends Fragment {

    private final int SEGUNDOS_MAX = 13000;
    private final static String FICHERO = "USER_PREFS";

    RadioGroup radioGroup;
    RadioButton rbTarea, rbExamen;

    MaterialCalendarView calendario;

    Button crearActividadBtn, darTiempoBtn;
    ImageButton ajustesBtn;

    Usuario u;

    Map<String, List<Actividad>> actividadesPorFecha;

    public CalendarFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        obtenerUsuario();
        cargarActividades();

        radioGroup = view.findViewById(R.id.radioGroupTipo);
        rbTarea = view.findViewById(R.id.rbTarea);
        rbExamen = view.findViewById(R.id.rbExamen);
        iniciarDeseleccionRadioGroup();
        iniciarRadioGroup();

        calendario = view.findViewById(R.id.calendario);

        crearActividadBtn = view.findViewById(R.id.crearActividadBtn);
        darTiempoBtn = view.findViewById(R.id.darTiempoBtn);
        ajustesBtn = view.findViewById(R.id.ajustesBtn);
        darTiempoBtn.setOnClickListener(v ->navegarDarTiempo());
        ajustesBtn.setOnClickListener(v -> navegarAjustes());
        crearActividadBtn.setOnClickListener(v -> navegarActividad());
        cambiarEstadoBotones();
        marcarTareasExamenes();
    }

    private void iniciarRadioGroup() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1) {
                marcarTareasExamenes();
            } else if (checkedId == R.id.rbTarea) {
                marcarTareas();
            } else if (checkedId == R.id.rbExamen) {
                MarcarExamenes();
            }
        });
    }


    private void cargarActividades() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(requireContext());
                List<Actividad> actividades = db.actividadDao().getAllOrderByFecha();
                actividadesPorFecha = new LinkedHashMap<>();

                for (Actividad a : actividades) {
                    actividadesPorFecha.putIfAbsent(a.getFecha_entrega(), new ArrayList<>());
                    actividadesPorFecha.get(a.getFecha_entrega()).add(a);
                }
            });
        }
    }


    private void marcarTareasExamenes() {
        calendario.removeDecorators();
        actividadesPorFecha.forEach((fecha, actividades) -> {
            int[] segundos = calcularSegundosPorDiaDeActividades(actividades);
            int secsTarea = segundos[0];
            int secsExamen = segundos[1];

            calendario.addDecorator(
                    new DayMultiColorCircularDecorator(
                            convertirFecha(fecha),
                            Color.BLUE, (float) secsTarea /SEGUNDOS_MAX,
                            Color.RED, (float) secsExamen /SEGUNDOS_MAX
                    )
            );

            Log.d("ACT_FECHA", convertirFecha(fecha).toString());
            Log.d("ACT_TEST", "ActsFecha: " + fecha + " segundosTarea: " + secsTarea + " segundosExamen: " + secsExamen);
        });
        Log.d("ACT_TEST", "Tareas y Examenes marcados");
    }


    private int[] calcularSegundosPorDiaDeActividades(List<Actividad> actividades) {
        int secsExamen = 0;
        int secsTarea = 0;
        for (Actividad a : actividades) {
            if (a.getTipo().equals("examen")) secsExamen += a.getSegundos_estimados();
            else secsTarea += a.getSegundos_estimados();
        }
        return new int[]{secsTarea, secsExamen};
    }


    private void marcarActividades(String tipo) {
        int color = (tipo.equals("examen"))? Color.BLUE : Color.RED;
        actividadesPorFecha.forEach((fecha, actividades) -> {
            int segundos = 0;
            for (Actividad a : actividades) {
                if (a.getTipo().equals(tipo)) segundos += a.getSegundos_estimados();
            }

            calendario.addDecorator(
                    new DayMultiColorCircularDecorator(
                            convertirFecha(fecha),
                            color, (float) segundos /SEGUNDOS_MAX
                    )
            );

            Log.d("ACT_TEST", "ExamenFecha: " + fecha + " segundos: " + segundos);
        });
    }

    private void marcarTareas() {
        calendario.removeDecorators();
        marcarActividades("tarea");
        Log.d("ACT_TEST", "Tareas marcadas");
        calendario.addDecorator(
                new DayMultiColorCircularDecorator(
                        convertirFecha("2025-11-1"),
                        1, Color.BLUE
                )
        );

    }

    private void MarcarExamenes() {
        calendario.removeDecorators();
        marcarActividades("examen");
        Log.d("ACT_TEST", "Examenes marcados");
    }

    private void iniciarDeseleccionRadioGroup() {
        final RadioButton[] lastChecked = {null};

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;

                radioButton.setOnClickListener(v -> {
                    if (lastChecked[0] == radioButton) {
                        radioButton.setChecked(false);
                        lastChecked[0] = null;
                        radioGroup.clearCheck();
                    } else {
                        lastChecked[0] = radioButton;
                    }
                });
            }
        }
    }

    private void debugMostrarUsuarios() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(requireContext());
                for (Usuario u : db.usuarioDao().getAll()) {
                    Log.d("DB_TEST", "Usuario: " + u.getNombre() + " - " + u.getId());
                }
            });
        }
    }

    private void cambiarEstadoBotones() {
        if (u.getRol().equals("profesor")) {
            darTiempoBtn.setVisibility(View.VISIBLE);
            crearActividadBtn.setVisibility(View.VISIBLE);
        } else {
            darTiempoBtn.setVisibility(View.GONE);
            crearActividadBtn.setVisibility(View.GONE);
        }
    }

    private void obtenerUsuario() {
        int id = requireActivity()
                .getSharedPreferences(FICHERO, Context.MODE_PRIVATE)
                .getInt("user_id", -1);
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(requireContext());
                u = db.usuarioDao().getById(id);
            });
        }
    }

    private void navegarAjustes() {
        calendario.removeDecorators();
        startActivity(new Intent(getActivity(), SettingsActivity.class));
    }

    private void navegarActividad() {
        Fragment nuevoFragment = new ActivityFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, nuevoFragment)
                .addToBackStack(null)
                .commit();
    }
    private void navegarDarTiempo() {
        Fragment nuevoFragment = new TimeFragment();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, nuevoFragment)
                .addToBackStack(null)
                .commit();
    }

}
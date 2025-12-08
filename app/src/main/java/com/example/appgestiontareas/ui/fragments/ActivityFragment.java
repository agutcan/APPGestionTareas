package com.example.appgestiontareas.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.activities.SettingsActivity;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;
import com.example.appgestiontareas.ui.utils.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActivityFragment extends Fragment {

    public ActivityFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    private final int SEGUNDOS_MAX = 13000;

    EditText inputAsignacion, inputEntrega, inputTitulo, inputTiempo;
    RadioGroup grupoTipo;
    int idAsignatura, idProfesor;
    private final static String FICHERO = "USER_PREFS";

    String fechaEntrega;

    Map<String, List<Actividad>> actividadesPorFecha;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        inputAsignacion = view.findViewById(R.id.inputAsignacion);
        inputEntrega = view.findViewById(R.id.inputEntrega);
        inputTiempo = view.findViewById(R.id.inputTiempo);
        ImageButton btnAjustes = view.findViewById(R.id.ajustesBtn3);
        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });

        Calendar calendario = Calendar.getInstance();

        // ----------- FECHA ASIGNACIÓN -------------
        inputAsignacion.setOnClickListener(v -> {
            int año = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    getContext(),
                    (view1, year, month, dayOfMonth) -> {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        inputAsignacion.setText(fecha);
                    },
                    año, mes, dia
            );
            datePicker.show();
        });

        // ----------- FECHA ENTREGA -------------
        inputEntrega.setOnClickListener(v -> {
            int año = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    getContext(),
                    (view12, year, month, dayOfMonth) -> {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        inputEntrega.setText(fecha);
                    },
                    año, mes, dia
            );
            datePicker.show();
        });

        Button btnCrear = view.findViewById(R.id.btnCrear);
        grupoTipo = view.findViewById(R.id.grupoTipo);
        inputTitulo = view.findViewById(R.id.inputTitulo);

        btnCrear.setOnClickListener(v -> {

            String fechaAsignacion = inputAsignacion.getText().toString().trim();
            fechaEntrega = inputEntrega.getText().toString().trim();
            String titulo = inputTitulo.getText().toString().trim();
            int tiempoSegundos = utils.hoursToSeconds(Integer.parseInt(inputTiempo.getText().toString()));

            int seleccionado = grupoTipo.getCheckedRadioButtonId();
            String tipo = (seleccionado == R.id.rbTarea) ? "tarea" : "examen";

            if (fechaAsignacion.isEmpty() || fechaEntrega.isEmpty()) {
                Toast.makeText(getContext(), "Debe seleccionar ambas fechas", Toast.LENGTH_SHORT).show();
                return;
            }

            fechaAsignacion = utils.normalizarFecha(fechaAsignacion);
            fechaEntrega = utils.normalizarFecha(fechaEntrega);

            idProfesor = requireActivity()
                    .getSharedPreferences(FICHERO, Context.MODE_PRIVATE)
                    .getInt("user_id", -1);

            cargarActividades();
            List<Actividad> todas = new ArrayList<>();

            for (List<Actividad> lista : actividadesPorFecha.values()) {
                todas.addAll(lista);
            }

            int[] tiempo = calcularSegundosActividades(todas);

            // ----- TODO dentro del mismo hilo -----
            new Thread(() -> {

                AppDatabase db = AppDatabase.getInstance(getContext());

                // Obtener asignatura del profesor
                Asignatura asignatura = db.asignaturaDao().getAsignaturaPorProfesor(idProfesor);

                if (asignatura == null) {
                    Log.e("ActivityFragment", "ERROR: El profesor no tiene asignaturas asignadas");
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "No tienes asignaturas asignadas", Toast.LENGTH_SHORT).show()
                    );
                    return;
                }

                UsuarioDao usuarioDao = db.usuarioDao();

                int tiempoDisponible = usuarioDao.obtenerTiempo(idProfesor);
                int suma = 0;
                for (int valor : tiempo) {
                        suma+=valor;
                    }
                if (suma > SEGUNDOS_MAX) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "No tiene tiempo suficiente hoy como para crear esta actividad", Toast.LENGTH_SHORT).show()
                    );
                    return;
                }

                if (tiempoSegundos > tiempoDisponible) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "No tiene tiempo suficiente como para crear esta actividad", Toast.LENGTH_SHORT).show()
                    );
                    return;
                }

                int idAsignatura = asignatura.getId();
                Log.d("ActivityFragment", "Asignatura encontrada: " + idAsignatura);

                // Crear actividad
                Actividad actividad = new Actividad();
                actividad.setId_asignatura(idAsignatura);
                actividad.setTitulo(titulo);
                actividad.setFecha_entrega(fechaEntrega);
                actividad.setTipo(tipo);
                actividad.setSegundos_estimados(tiempoSegundos);
                actividad.setCreado_por(idProfesor);

                long resultado = db.actividadDao().insert(actividad);

                Log.d("ActivityFragment", "Actividad creada con ID: " + resultado);

                usuarioDao.actualizarTiempo(idProfesor, tiempoDisponible - tiempoSegundos);

                Log.d("TimeUpdate", "Tiempo actualizado para " + idProfesor + " a " + (tiempoDisponible - tiempoSegundos));
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Actividad creada correctamente", Toast.LENGTH_SHORT).show()
                );

            }).start();
        });





    }
    private void cargarActividades() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(getContext());
                List<Actividad> actividades = db.actividadDao().getAllOrderByFecha();
                actividadesPorFecha = new LinkedHashMap<>();

                for (Actividad a : actividades) {
                    actividadesPorFecha.putIfAbsent(a.getFecha_entrega(), new ArrayList<>());
                    actividadesPorFecha.get(a.getFecha_entrega()).add(a);
                }
            });
        }
    }

    private int[] calcularSegundosActividades(List<Actividad> actividades) {
        int secsExamen = 0;
        int secsTarea = 0;
        for (Actividad a : actividades) {
            if (a.getTipo().equals("examen")) secsExamen += a.getSegundos_estimados();
            else secsTarea += a.getSegundos_estimados();
        }
        return new int[]{secsTarea, secsExamen};
    }
}
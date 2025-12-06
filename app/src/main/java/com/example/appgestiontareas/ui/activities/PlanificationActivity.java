package com.example.appgestiontareas.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.decorators.DayMultiColorCircularDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlanificationActivity extends AppCompatActivity {

    private final int SEGUNDOS_MAX = 36000;

    RadioGroup radioGroup;
    RadioButton rbTarea, rbExamen;

    MaterialCalendarView calendario;

    Button crearActividadBtn, darTiempoBtn;
    ImageButton ajustesBtn;

    Usuario u;

    Map<String, List<Actividad>> actividadesPorFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //DatabaseEjemplo.ejecutar(this);

        // ids: prof 8, 0 estudiante
        obtenerUsuario();
        cargarActividades();

        radioGroup = findViewById(R.id.radioGroupTipo);
        rbTarea = findViewById(R.id.rbTarea);
        rbExamen = findViewById(R.id.rbExamen);
        iniciarDeseleccionRadioGroup();
        iniciarRadioGroup();

        calendario = findViewById(R.id.calendario);

        crearActividadBtn = findViewById(R.id.crearActividadBtn);
        darTiempoBtn = findViewById(R.id.darTiempoBtn);
        ajustesBtn = findViewById(R.id.ajustesBtn);
        ajustesBtn.setOnClickListener(v -> navegarAjustes());
        darTiempoBtn.setOnClickListener(v -> {

            calendario.addDecorator(
                    new DayMultiColorCircularDecorator(
                            CalendarDay.from(2025, 11, 12),
                            Color.RED,
                            0.7f
                    )
            );

            calendario.addDecorator(
                    new DayMultiColorCircularDecorator(
                            CalendarDay.from(2025, 11, 13),
                            Color.GREEN, 0.4f,
                            Color.RED,  0.3f
                    )
            );
        });





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
                AppDatabase db = AppDatabase.getInstance(this);
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
            int[] segundos = calcularSegundosActividades(actividades);
            int secsTarea = segundos[0];
            int secsExamen = segundos[1];

            calendario.addDecorator(
                    new DayMultiColorCircularDecorator(
                            CalendarDay.from(convertirFecha(fecha)),
                            secsTarea, Color.BLUE,
                            secsExamen, Color.RED
                    )
            );
        });
        Log.d("ACT_TEST", "TareasExamenes");
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

    private Date convertirFecha(String fecha) {
        String[] partes = fecha.split("-");
        int year = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]) - 1;
        int day = Integer.parseInt(partes[2]);
        return new Date(year, month, day);
    }


    private void marcarActividades(String tipo) {
        int color = (tipo.equals("examen"))? Color.BLUE : Color.RED;
        actividadesPorFecha.forEach((fecha, actividades) -> {
            int segundos = 0;
            for (Actividad a : actividades) {
                if (a.getTipo().equals(tipo)) segundos += a.getSegundos_estimados();
            }
            if (segundos > 0) {
                calendario.addDecorator(
                        new DayMultiColorCircularDecorator(
                                CalendarDay.from(convertirFecha(fecha)),
                                segundos, color
                        )
                );
            }
            Log.d("ACT_TEST", "ExamenFecha: " + fecha + " segundos: " + segundos);
        });
    }

    private void marcarTareas() {
        calendario.removeDecorators();
        marcarActividades("tarea");
        Log.d("ACT_TEST", "tareas");
    }

    private void MarcarExamenes() {
        calendario.removeDecorators();
        marcarActividades("examen");
        Log.d("ACT_TEST", "examenes");
    }

    private void iniciarDeseleccionRadioGroup() {
        final RadioButton[] lastChecked = {null};

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;

                radioButton.setOnClickListener(v -> {
                    if (lastChecked[0] == radioButton) {
                        // Si pulsa el mismo → deseleccionar
                        radioButton.setChecked(false);
                        lastChecked[0] = null;
                        radioGroup.clearCheck();
                    } else {
                        // Nuevo seleccionado
                        lastChecked[0] = radioButton;
                    }
                });
            }
        }
    }

    private void debugMostrarUsuarios() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(this);
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
        int id = 8; // 8 prof, 1 estudiante
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(this);
                u = db.usuarioDao().getById(id);
            });
        }
    }

    private void navegarAjustes() {
        calendario.removeDecorators();
        //startActivity(new Intent(this, SettingsActivity.class));
    }

    private void navegarDarTiempo() {
        //startActivity(new Intent(this, SettingsActivity.class));
    }

}
package com.example.appgestiontareas.ui.database.ejemplos;

import android.content.Context;
import android.util.Log;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.database.entidades.Curso;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.RegistroTiempo;
import com.example.appgestiontareas.ui.database.entidades.Planificacion;
import com.example.appgestiontareas.ui.database.entidades.Bienestar;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;

import java.util.List;

public class DatabaseEjemplo {

    public static void ejecutar(Context context) {

        AppDatabase db = AppDatabase.getInstance(context);

        new Thread(() -> {
            Log.d("DB_TEST", "---- INICIO DE PRUEBAS ----");

            // -----------------------------------------
            // 1) INSERTAR USUARIO
            // -----------------------------------------
            Usuario usuario = new Usuario("Andrea", "andrea@email.com", "1234", "estudiante");
            long userId = db.usuarioDao().insert(usuario);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId);

            // -----------------------------------------
            // 2) INSERTAR CURSO
            // -----------------------------------------
            Curso curso = new Curso("Programación DAM", 20, 2);
            long cursoId = db.cursoDao().insert(curso);
            Log.d("DB_TEST", "Curso creado con ID: " + cursoId);

            // -----------------------------------------
            // 3) INSERTAR ASIGNATURA
            // -----------------------------------------
            Asignatura asignatura = new Asignatura((int) cursoId, "Base de Datos", 4);
            long asignaturaId = db.asignaturaDao().insert(asignatura);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId);

            // -----------------------------------------
            // 4) INSERTAR ACTIVIDAD (TAREA)
            // -----------------------------------------
            Actividad tarea = new Actividad((int) asignaturaId, (int) userId, "Trabajo SQL", "tarea", "2025-01-15");
            long tareaId = db.actividadDao().insert(tarea);
            Log.d("DB_TEST", "Actividad Tarea creada con ID: " + tareaId);

            // -----------------------------------------
            // 5) INSERTAR ACTIVIDAD (EXAMEN)
            // -----------------------------------------
            Actividad examen = new Actividad((int) asignaturaId, (int) userId, "Examen Tema 1", "examen", "2025-01-20");
            long examenId = db.actividadDao().insert(examen);
            Log.d("DB_TEST", "Actividad Examen creada con ID: " + examenId);

            // -----------------------------------------
            // 6) INSERTAR REGISTRO DE TIEMPO
            // -----------------------------------------
            RegistroTiempo tiempo = new RegistroTiempo((int) userId, (int) tareaId, 45, "2025-01-10");
            long tiempoId = db.registroTiempoDao().insert(tiempo);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoId);

            // -----------------------------------------
            // 7) INSERTAR PLANIFICACIÓN
            // -----------------------------------------
            Planificacion plan = new Planificacion((int) userId, (int) tareaId, "2025-01-12", 60);
            long planId = db.planificacionDao().insert(plan);
            Log.d("DB_TEST", "Planificación creada con ID: " + planId);

            // -----------------------------------------
            // 8) INSERTAR BIENESTAR
            // -----------------------------------------
            Bienestar bienestar = new Bienestar((int) userId, "2025-01-11", "estres", 6, "Sin notas");
            long bienestarId = db.bienestarDao().insert(bienestar);
            Log.d("DB_TEST", "Bienestar creado con ID: " + bienestarId);

            // -----------------------------------------
            // 9) INSERTAR TIEMPO PROFESOR
            // -----------------------------------------
            TiempoProfesor prof = new TiempoProfesor((int) userId, 0, 120, "Corrección de exámenes", 3);
            long profId = db.tiempoProfesorDao().insert(prof);
            Log.d("DB_TEST", "TiempoProfesor creado con ID: " + profId);

            // -----------------------------------------
            // CONSULTAS DE PRUEBA
            // -----------------------------------------
            List<Actividad> actividades = db.actividadDao().getAll();
            Log.d("DB_TEST", "-- LISTA DE ACTIVIDADES --");
            for (Actividad a : actividades) {
                Log.d("DB_TEST", a.getTitulo() + " (" + a.getTipo() + ")");
            }

            List<Bienestar> bienestarList = db.bienestarDao().getAll();
            Log.d("DB_TEST", "-- ESTADO DE BIENESTAR --");
            for (Bienestar b : bienestarList) {
                Log.d("DB_TEST", b.getEstado_animo() + ": " + b.getHoras_sueno() + "h sueño");
            }

            Log.d("DB_TEST", "---- FIN DE PRUEBAS ----");

        }).start();
    }
}

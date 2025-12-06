package com.example.appgestiontareas.ui.database.ejemplos;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.util.Log;

import com.example.appgestiontareas.ui.adapters.ActividadAdapter;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.database.entidades.Curso;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.RegistroTiempo;
import com.example.appgestiontareas.ui.database.entidades.Planificacion;
import com.example.appgestiontareas.ui.database.entidades.Bienestar;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;
import com.example.appgestiontareas.ui.database.repository.ActividadRepository;
import com.example.appgestiontareas.ui.database.repository.UsuarioRepository;

import java.util.List;

public class DatabaseEjemplo {

    public static void ejecutar(Context context) {

        AppDatabase db = AppDatabase.getInstance(context);

        new Thread(() -> {
            Log.d("DB_TEST", "---- INICIO DE PRUEBAS ----");

            // Usuarios
            List<Usuario> usuarios = db.usuarioDao().getAll();
            if (!usuarios.isEmpty()) {
                // Si ya hay datos no se hace nada
                return;
            }

            // -----------------------------------------
            // 1) INSERTAR USUARIO
            // -----------------------------------------

            Usuario usuario1 = new Usuario("Andrea", "andrea@email.com", "1234", "estudiante");
            long userId = db.usuarioDao().insert(usuario1);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId);

            Usuario usuario2 = new Usuario("Carlos", "carlos@gmail.com", "abcd", "estudiante");
            long userId2 = db.usuarioDao().insert(usuario2);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId2);

            Usuario usuario3 = new Usuario("Lucía", "lucia@gmail.com", "pass123", "estudiante");
            long userId3 = db.usuarioDao().insert(usuario3);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId3);

            Usuario usuario4 = new Usuario("Marcos", "marcos@hotmail.com", "qwerty", "estudiante");
            long userId4 = db.usuarioDao().insert(usuario4);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId4);

            Usuario usuario5 = new Usuario("Sofía", "sofia@yahoo.es", "pass999", "estudiante");
            long userId5 = db.usuarioDao().insert(usuario5);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId5);

            Usuario usuario6 = new Usuario("Javier", "javier@correo.com", "xyz123", "estudiante");
            long userId6 = db.usuarioDao().insert(usuario6);
            Log.d("DB_TEST", "Usuario creado con ID: " + userId6);

            // -----------------------------------------
            // 4) INSERTAR PROFESORES
            // -----------------------------------------

            Usuario profesor1 = new Usuario("Profesor Lengua", "lengua@email.com", "pass", "profesor", 200000);
            long profesorId1 = db.usuarioDao().insert(profesor1);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId1);

            Usuario profesor2 = new Usuario("Profesor Mates", "mates@email.com", "pass", "profesor", 200000);
            long profesorId2 = db.usuarioDao().insert(profesor2);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId2);

            Usuario profesor3 = new Usuario("Profesor Conocimientos del Medio", "cm@email.com", "pass", "profesor", 200000);
            long profesorId3 = db.usuarioDao().insert(profesor3);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId3);

            Usuario profesor4 = new Usuario("Profesor Religión", "religion@email.com", "pass", "profesor", 200000);
            long profesorId4 = db.usuarioDao().insert(profesor4);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId4);

            Usuario profesor5 = new Usuario("Profesor Educación Física", "ef@email.com", "pass", "profesor", 200000);
            long profesorId5 = db.usuarioDao().insert(profesor5);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId5);

            Usuario profesor6 = new Usuario("Profesor Música", "musica@email.com", "pass", "profesor", 200000);
            long profesorId6 = db.usuarioDao().insert(profesor6);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId6);

            Usuario profesor7 = new Usuario("Profesor Inglés", "ingles@email.com", "pass", "profesor", 200000);
            long profesorId7 = db.usuarioDao().insert(profesor7);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId7);

            Usuario profesor8 = new Usuario("Profesor Francés", "frances@email.com", "pass", "profesor", 200000);
            long profesorId8 = db.usuarioDao().insert(profesor8);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId8);

            Usuario profesor9 = new Usuario("Profesor Refuerzo/Lectura", "refuerzo@email.com", "pass", "profesor", 200000);
            long profesorId9 = db.usuarioDao().insert(profesor9);
            Log.d("DB_TEST", "Profesor creado con ID: " + profesorId9);


            // -----------------------------------------
            // 2) INSERTAR CURSO
            // -----------------------------------------

            Curso curso = new Curso("6º Primaria", 200000, 2);
            long cursoId = db.cursoDao().insert(curso);
            Log.d("DB_TEST", "Curso creado con ID: " + cursoId);

            // -----------------------------------------
            // 3) INSERTAR ASIGNATURA
            // -----------------------------------------

            Asignatura asignatura1 = new Asignatura((int) cursoId, "Lengua", 5);
            long asignaturaId = db.asignaturaDao().insert(asignatura1);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId);

            Asignatura asignatura2 = new Asignatura((int) cursoId, "Mates", 5);
            long asignaturaId2 = db.asignaturaDao().insert(asignatura2);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId2);

            Asignatura asignatura3 = new Asignatura((int) cursoId, "Conocimientos del Medio", 3);
            long asignaturaId3 = db.asignaturaDao().insert(asignatura3);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId3);

            Asignatura asignatura4 = new Asignatura((int) cursoId, "Religión", 1);
            long asignaturaId4 = db.asignaturaDao().insert(asignatura4);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId4);

            Asignatura asignatura5 = new Asignatura((int) cursoId, "Educación Física", 2);
            long asignaturaId5 = db.asignaturaDao().insert(asignatura5);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId5);

            Asignatura asignatura6 = new Asignatura((int) cursoId, "Música", 1);
            long asignaturaId6 = db.asignaturaDao().insert(asignatura6);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId6);

            Asignatura asignatura7 = new Asignatura((int) cursoId, "Inglés", 3);
            long asignaturaId7 = db.asignaturaDao().insert(asignatura7);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId7);

            Asignatura asignatura8 = new Asignatura((int) cursoId, "Francés", 2);
            long asignaturaId8 = db.asignaturaDao().insert(asignatura8);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId8);

            Asignatura asignatura9 = new Asignatura((int) cursoId, "Refuerzo/Lectura", 3);
            long asignaturaId9 = db.asignaturaDao().insert(asignatura9);
            Log.d("DB_TEST", "Asignatura creada con ID: " + asignaturaId9);

            // -----------------------------------------
            // 4) INSERTAR ACTIVIDAD (TAREA)
            // -----------------------------------------

            Actividad tarea1 = new Actividad((int) asignaturaId, (int) userId, "Redacción: Mi familia", "tarea", "2025-01-10");
            long tareaId = db.actividadDao().insert(tarea1);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId);

            Actividad tarea2 = new Actividad((int) asignaturaId2, (int) userId, "Ejercicios de fracciones", "tarea", "2025-01-12");
            long tareaId2 = db.actividadDao().insert(tarea2);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId2);

            Actividad tarea3 = new Actividad((int) asignaturaId3, (int) userId, "Mapa del sistema solar", "tarea", "2025-01-14");
            long tareaId3 = db.actividadDao().insert(tarea3);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId3);

            Actividad tarea4 = new Actividad((int) asignaturaId4, (int) userId, "Resumen parábolas", "tarea", "2025-01-11");
            long tareaId4 = db.actividadDao().insert(tarea4);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId4);

            Actividad tarea5 = new Actividad((int) asignaturaId5, (int) userId, "Circuito de resistencia", "tarea", "2025-01-09");
            long tareaId5 = db.actividadDao().insert(tarea5);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId5);

            Actividad tarea6 = new Actividad((int) asignaturaId6, (int) userId, "Aprender notas musicales", "tarea", "2025-01-13");
            long tareaId6 = db.actividadDao().insert(tarea6);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId6);

            Actividad tarea7 = new Actividad((int) asignaturaId7, (int) userId, "Worksheet: My Family", "tarea", "2025-01-15");
            long tareaId7 = db.actividadDao().insert(tarea7);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId7);

            Actividad tarea8 = new Actividad((int) asignaturaId8, (int) userId, "Fiche de vocabulaire", "tarea", "2025-01-16");
            long tareaId8 = db.actividadDao().insert(tarea8);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId8);

            Actividad tarea9 = new Actividad((int) asignaturaId9, (int) userId, "Lectura guiada capítulo 1", "tarea", "2025-01-17");
            long tareaId9 = db.actividadDao().insert(tarea9);
            Log.d("DB_TEST", "Tarea creada con ID: " + tareaId9);

            // -----------------------------------------
            // 5) INSERTAR ACTIVIDAD (EXAMEN)
            // -----------------------------------------

            Actividad examen1 = new Actividad((int) asignaturaId, (int) userId, "Examen de ortografía", "examen", "2025-01-20");
            long examenId = db.actividadDao().insert(examen1);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId);

            Actividad examen2 = new Actividad((int) asignaturaId2, (int) userId, "Examen de multiplicaciones", "examen", "2025-01-22");
            long examenId2 = db.actividadDao().insert(examen2);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId2);

            Actividad examen3 = new Actividad((int) asignaturaId3, (int) userId, "Examen de los animales", "examen", "2025-01-25");
            long examenId3 = db.actividadDao().insert(examen3);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId3);

            Actividad examen4 = new Actividad((int) asignaturaId4, (int) userId, "Examen valores cristianos", "examen", "2025-01-21");
            long examenId4 = db.actividadDao().insert(examen4);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId4);

            Actividad examen5 = new Actividad((int) asignaturaId5, (int) userId, "Prueba de velocidad 50m", "examen", "2025-01-19");
            long examenId5 = db.actividadDao().insert(examen5);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId5);

            Actividad examen6 = new Actividad((int) asignaturaId6, (int) userId, "Examen de ritmo básico", "examen", "2025-01-27");
            long examenId6 = db.actividadDao().insert(examen6);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId6);

            Actividad examen7 = new Actividad((int) asignaturaId7, (int) userId, "Test: Verb to be", "examen", "2025-01-30");
            long examenId7 = db.actividadDao().insert(examen7);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId7);

            Actividad examen8 = new Actividad((int) asignaturaId8, (int) userId, "Examen: Salutations", "examen", "2025-01-28");
            long examenId8 = db.actividadDao().insert(examen8);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId8);

            Actividad examen9 = new Actividad((int) asignaturaId9, (int) userId, "Prueba de comprensión lectora", "examen", "2025-01-29");
            long examenId9 = db.actividadDao().insert(examen9);
            Log.d("DB_TEST", "Examen creado con ID: " + examenId9);

            // -----------------------------------------
            // 6) INSERTAR REGISTROS DE TIEMPO PARA TODAS LAS TAREAS
            // -----------------------------------------

            RegistroTiempo tiempoT1 = new RegistroTiempo((int) userId, (int) tareaId, 45, "2025-01-10");
            long tiempoT1Id = db.registroTiempoDao().insert(tiempoT1);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT1Id);

            RegistroTiempo tiempoT2 = new RegistroTiempo((int) userId, (int) tareaId2, 50, "2025-01-12");
            long tiempoT2Id = db.registroTiempoDao().insert(tiempoT2);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT2Id);

            RegistroTiempo tiempoT3 = new RegistroTiempo((int) userId, (int) tareaId3, 40, "2025-01-14");
            long tiempoT3Id = db.registroTiempoDao().insert(tiempoT3);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT3Id);

            RegistroTiempo tiempoT4 = new RegistroTiempo((int) userId, (int) tareaId4, 30, "2025-01-11");
            long tiempoT4Id = db.registroTiempoDao().insert(tiempoT4);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT4Id);

            RegistroTiempo tiempoT5 = new RegistroTiempo((int) userId, (int) tareaId5, 20, "2025-01-09");
            long tiempoT5Id = db.registroTiempoDao().insert(tiempoT5);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT5Id);

            RegistroTiempo tiempoT6 = new RegistroTiempo((int) userId, (int) tareaId6, 35, "2025-01-13");
            long tiempoT6Id = db.registroTiempoDao().insert(tiempoT6);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT6Id);

            RegistroTiempo tiempoT7 = new RegistroTiempo((int) userId, (int) tareaId7, 50, "2025-01-15");
            long tiempoT7Id = db.registroTiempoDao().insert(tiempoT7);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT7Id);

            RegistroTiempo tiempoT8 = new RegistroTiempo((int) userId, (int) tareaId8, 55, "2025-01-16");
            long tiempoT8Id = db.registroTiempoDao().insert(tiempoT8);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT8Id);

            RegistroTiempo tiempoT9 = new RegistroTiempo((int) userId, (int) tareaId9, 25, "2025-01-17");
            long tiempoT9Id = db.registroTiempoDao().insert(tiempoT9);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoT9Id);


            // -----------------------------------------
            // 7) INSERTAR REGISTROS DE TIEMPO PARA TODOS LOS EXÁMENES
            // -----------------------------------------

            RegistroTiempo tiempoE1 = new RegistroTiempo((int) userId, (int) examenId, 60, "2025-01-20");
            long tiempoE1Id = db.registroTiempoDao().insert(tiempoE1);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE1Id);

            RegistroTiempo tiempoE2 = new RegistroTiempo((int) userId, (int) examenId2, 70, "2025-01-22");
            long tiempoE2Id = db.registroTiempoDao().insert(tiempoE2);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE2Id);

            RegistroTiempo tiempoE3 = new RegistroTiempo((int) userId, (int) examenId3, 55, "2025-01-25");
            long tiempoE3Id = db.registroTiempoDao().insert(tiempoE3);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE3Id);

            RegistroTiempo tiempoE4 = new RegistroTiempo((int) userId, (int) examenId4, 35, "2025-01-21");
            long tiempoE4Id = db.registroTiempoDao().insert(tiempoE4);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE4Id);

            RegistroTiempo tiempoE5 = new RegistroTiempo((int) userId, (int) examenId5, 25, "2025-01-19");
            long tiempoE5Id = db.registroTiempoDao().insert(tiempoE5);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE5Id);

            RegistroTiempo tiempoE6 = new RegistroTiempo((int) userId, (int) examenId6, 50, "2025-01-27");
            long tiempoE6Id = db.registroTiempoDao().insert(tiempoE6);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE6Id);

            RegistroTiempo tiempoE7 = new RegistroTiempo((int) userId, (int) examenId7, 80, "2025-01-30");
            long tiempoE7Id = db.registroTiempoDao().insert(tiempoE7);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE7Id);

            RegistroTiempo tiempoE8 = new RegistroTiempo((int) userId, (int) examenId8, 65, "2025-01-28");
            long tiempoE8Id = db.registroTiempoDao().insert(tiempoE8);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE8Id);

            RegistroTiempo tiempoE9 = new RegistroTiempo((int) userId, (int) examenId9, 40, "2025-01-29");
            long tiempoE9Id = db.registroTiempoDao().insert(tiempoE9);
            Log.d("DB_TEST", "RegistroTiempo creado con ID: " + tiempoE9Id);

            // -----------------------------------------
            // 7) INSERTAR PLANIFICACIÓN PARA TODAS LAS TAREAS
            // -----------------------------------------

            Planificacion planT1 = new Planificacion((int) userId, (int) tareaId, "2025-01-10", 45);
            long planT1Id = db.planificacionDao().insert(planT1);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT1Id);

            Planificacion planT2 = new Planificacion((int) userId, (int) tareaId2, "2025-01-12", 50);
            long planT2Id = db.planificacionDao().insert(planT2);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT2Id);

            Planificacion planT3 = new Planificacion((int) userId, (int) tareaId3, "2025-01-14", 40);
            long planT3Id = db.planificacionDao().insert(planT3);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT3Id);

            Planificacion planT4 = new Planificacion((int) userId, (int) tareaId4, "2025-01-11", 30);
            long planT4Id = db.planificacionDao().insert(planT4);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT4Id);

            Planificacion planT5 = new Planificacion((int) userId, (int) tareaId5, "2025-01-09", 20);
            long planT5Id = db.planificacionDao().insert(planT5);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT5Id);

            Planificacion planT6 = new Planificacion((int) userId, (int) tareaId6, "2025-01-13", 35);
            long planT6Id = db.planificacionDao().insert(planT6);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT6Id);

            Planificacion planT7 = new Planificacion((int) userId, (int) tareaId7, "2025-01-15", 50);
            long planT7Id = db.planificacionDao().insert(planT7);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT7Id);

            Planificacion planT8 = new Planificacion((int) userId, (int) tareaId8, "2025-01-16", 55);
            long planT8Id = db.planificacionDao().insert(planT8);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT8Id);

            Planificacion planT9 = new Planificacion((int) userId, (int) tareaId9, "2025-01-17", 25);
            long planT9Id = db.planificacionDao().insert(planT9);
            Log.d("DB_TEST", "Planificación creada con ID: " + planT9Id);


            // -----------------------------------------
            // 8) INSERTAR PLANIFICACIÓN PARA TODOS LOS EXÁMENES
            // -----------------------------------------

            Planificacion planE1 = new Planificacion((int) userId, (int) examenId, "2025-01-20", 60);
            long planE1Id = db.planificacionDao().insert(planE1);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE1Id);

            Planificacion planE2 = new Planificacion((int) userId, (int) examenId2, "2025-01-22", 70);
            long planE2Id = db.planificacionDao().insert(planE2);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE2Id);

            Planificacion planE3 = new Planificacion((int) userId, (int) examenId3, "2025-01-25", 55);
            long planE3Id = db.planificacionDao().insert(planE3);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE3Id);

            Planificacion planE4 = new Planificacion((int) userId, (int) examenId4, "2025-01-21", 35);
            long planE4Id = db.planificacionDao().insert(planE4);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE4Id);

            Planificacion planE5 = new Planificacion((int) userId, (int) examenId5, "2025-01-19", 25);
            long planE5Id = db.planificacionDao().insert(planE5);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE5Id);

            Planificacion planE6 = new Planificacion((int) userId, (int) examenId6, "2025-01-27", 50);
            long planE6Id = db.planificacionDao().insert(planE6);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE6Id);

            Planificacion planE7 = new Planificacion((int) userId, (int) examenId7, "2025-01-30", 80);
            long planE7Id = db.planificacionDao().insert(planE7);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE7Id);

            Planificacion planE8 = new Planificacion((int) userId, (int) examenId8, "2025-01-28", 65);
            long planE8Id = db.planificacionDao().insert(planE8);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE8Id);

            Planificacion planE9 = new Planificacion((int) userId, (int) examenId9, "2025-01-29", 40);
            long planE9Id = db.planificacionDao().insert(planE9);
            Log.d("DB_TEST", "Planificación creada con ID: " + planE9Id);


            // -----------------------------------------
            // 8) INSERTAR BIENESTAR
            // -----------------------------------------

            Bienestar bienestar = new Bienestar((int) userId, "2025-01-11", "estres", 6, "Sin notas");
            long bienestarId = db.bienestarDao().insert(bienestar);
            Log.d("DB_TEST", "Bienestar creado con ID: " + bienestarId);

            // -----------------------------------------
            // 9) INSERTAR TIEMPO PROFESOR
            // -----------------------------------------

            Usuario profesorX = new Usuario("Profesor X", "profesorx@email.com", "pass", "profesor", 200000);
            long profesorId = db.usuarioDao().insert(profesorX);
            Usuario profesorX2 = new Usuario("Profesor X2", "profesorx2@email.com", "pass", "profesor", 200000);
            long profesor2Id = db.usuarioDao().insert(profesorX2);
            TiempoProfesor prof = new TiempoProfesor((int) profesor2Id, (int) profesorId, 1200, "prestar", 3);
            long profId = db.tiempoProfesorDao().insert(prof);
            Log.d("DB_TEST", "TiempoProfesor creado con ID: " + profId);


            // ====== ACTUALIZAR TIEMPO SOLO SI ORIGEN ES PROFESOR ======

            Usuario usuarioOrigen = db.usuarioDao().getById(prof.getId_profesor_origen());
            Usuario usuarioDestino = db.usuarioDao().getById(prof.getId_profesor_destino());

            if (usuarioOrigen == null || usuarioDestino == null) {
                Log.d("DB_TEST", "Error: Usuario origen o destino no existe");
                return; // salir si algo falla
            }

            // Solo si el rol del origen es profesor
            if (!"profesor".equalsIgnoreCase(usuarioOrigen.getRol())) {
                Log.d("DB_TEST", "Acción no permitida: el usuario origen no es profesor");
                return;
            }

            Integer origenTiempoActual = usuarioOrigen.getTiempo();
            Integer destinoTiempoActual = usuarioDestino.getTiempo();

            if (origenTiempoActual == null) origenTiempoActual = 0;
            if (destinoTiempoActual == null) destinoTiempoActual = 0;

            int segundos = prof.getSegundos();

            // Evitar tiempo negativo
            int tiempoRestanteOrigen = Math.max(origenTiempoActual - segundos, 0);
            int tiempoTransferido = origenTiempoActual - tiempoRestanteOrigen;
            int tiempoDestinoNuevo = destinoTiempoActual + tiempoTransferido;

            if (prof.getTipo().equals("prestar")) {
                db.usuarioDao().actualizarTiempo(prof.getId_profesor_origen(), tiempoRestanteOrigen);
                db.usuarioDao().actualizarTiempo(prof.getId_profesor_destino(), tiempoDestinoNuevo);
            } else if (prof.getTipo().equals("regalar")) {
                // Regalar no resta al origen
                db.usuarioDao().actualizarTiempo(prof.getId_profesor_destino(), destinoTiempoActual + segundos);
            }

            Log.d("DB_TEST", "Tiempo actualizado ORIGEN=" + Math.max(tiempoRestanteOrigen, 0) +
                    " DESTINO=" + (prof.getTipo().equals("prestar") ? tiempoDestinoNuevo : destinoTiempoActual + segundos));

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
                Log.d("DB_TEST", b.getEstado_animo() + ": " + b.getSegundos_sueno() + "h sueño");
            }

            Log.d("DB_TEST", "---- FIN DE PRUEBAS ----");

        }).start();
    }
}

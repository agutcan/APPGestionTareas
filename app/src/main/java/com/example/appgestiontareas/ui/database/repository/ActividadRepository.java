package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.ActividadDao;
import com.example.appgestiontareas.ui.database.entidades.Actividad;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActividadRepository {

    private final ActividadDao actividadDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public ActividadRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        actividadDao = db.actividadDao();
    }

    // ============ INSERT ============
    public void insert(Actividad actividad) {
        executor.execute(() -> actividadDao.insert(actividad));
    }

    // ============ UPDATE ============
    public void update(Actividad actividad) {
        executor.execute(() -> actividadDao.update(actividad));
    }

    // ============ DELETE ============
    public void delete(Actividad actividad) {
        executor.execute(() -> actividadDao.delete(actividad));
    }

    // ============ DELETE ALL ============
    public void deleteAll() {
        executor.execute(actividadDao::deleteAll);
    }

    // ============ GET ALL ============
    public interface CallbackLista {
        void onResult(List<Actividad> lista);
    }

    public void getAll(CallbackLista callback) {
        executor.execute(() -> {
            List<Actividad> list = actividadDao.getAll();
            callback.onResult(list);
        });
    }

    // ============ GET BY ID ============
    public interface CallbackItem {
        void onResult(Actividad actividad);
    }

    public void getById(int id, CallbackItem callback) {
        executor.execute(() -> {
            Actividad a = actividadDao.getById(id);
            callback.onResult(a);
        });
    }

    // ============ GET BY ALUMNO ============
    public void getByAlumno(int idAlumno, CallbackLista callback) {
        executor.execute(() -> {
            List<Actividad> lista = actividadDao.getByAlumno(idAlumno);
            callback.onResult(lista);
        });
    }

    // ============ GET BY ASIGNATURA ============
    public void getByAsignatura(int idAsignatura, CallbackLista callback) {
        executor.execute(() -> {
            List<Actividad> lista = actividadDao.getByAsignatura(idAsignatura);
            callback.onResult(lista);
        });
    }

    // ============ GET BY TIPO (tarea/examen) ============
    public void getByTipo(String tipo, CallbackLista callback) {
        executor.execute(() -> {
            List<Actividad> lista = actividadDao.getByTipo(tipo);
            callback.onResult(lista);
        });
    }
}

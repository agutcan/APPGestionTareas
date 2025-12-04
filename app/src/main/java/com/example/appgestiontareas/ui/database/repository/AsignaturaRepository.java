package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.AsignaturaDao;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;

import java.util.List;

public class AsignaturaRepository {

    private final AsignaturaDao asignaturaDao;

    public AsignaturaRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.asignaturaDao = db.asignaturaDao();
    }

    // INSERT
    public long insert(Asignatura asignatura) {
        return asignaturaDao.insert(asignatura);
    }

    // UPDATE
    public void update(Asignatura asignatura) {
        asignaturaDao.update(asignatura);
    }

    // DELETE
    public void delete(Asignatura asignatura) {
        asignaturaDao.delete(asignatura);
    }

    // DELETE ALL
    public void deleteAll() {
        asignaturaDao.deleteAll();
    }

    // GET ALL
    public List<Asignatura> getAll() {
        return asignaturaDao.getAll();
    }

    // GET BY ID
    public Asignatura getById(int id) {
        return asignaturaDao.getById(id);
    }

    // GET BY CURSO
    public List<Asignatura> getByCurso(int idCurso) {
        return asignaturaDao.getByCurso(idCurso);
    }
}

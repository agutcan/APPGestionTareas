package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.BienestarDao;
import com.example.appgestiontareas.ui.database.entidades.Bienestar;

import java.util.List;

public class BienestarRepository {

    private final BienestarDao bienestarDao;

    public BienestarRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.bienestarDao = db.bienestarDao();
    }

    // INSERT
    public long insert(Bienestar bienestar) {
        return bienestarDao.insert(bienestar);
    }

    // UPDATE
    public void update(Bienestar bienestar) {
        bienestarDao.update(bienestar);
    }

    // DELETE
    public void delete(Bienestar bienestar) {
        bienestarDao.delete(bienestar);
    }

    // DELETE ALL
    public void deleteAll() {
        bienestarDao.deleteAll();
    }

    // GET ALL
    public List<Bienestar> getAll() {
        return bienestarDao.getAll();
    }

    // GET BY ID
    public Bienestar getById(int id) {
        return bienestarDao.getById(id);
    }

    // GET BY ALUMNO
    public List<Bienestar> getByAlumno(int idAlumno) {
        return bienestarDao.getByAlumno(idAlumno);
    }
}

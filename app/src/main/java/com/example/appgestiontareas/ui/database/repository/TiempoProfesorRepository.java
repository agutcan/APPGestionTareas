package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.TiempoProfesorDao;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;

import java.util.List;

public class TiempoProfesorRepository {

    private TiempoProfesorDao tiempoProfesorDao;

    public TiempoProfesorRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        tiempoProfesorDao = db.tiempoProfesorDao();
    }

    // ===== CRUD =====

    public long insert(TiempoProfesor tiempoProfesor) {
        return tiempoProfesorDao.insert(tiempoProfesor);
    }

    public void update(TiempoProfesor tiempoProfesor) {
        tiempoProfesorDao.update(tiempoProfesor);
    }

    public void delete(TiempoProfesor tiempoProfesor) {
        tiempoProfesorDao.delete(tiempoProfesor);
    }

    public void deleteAll() {
        tiempoProfesorDao.deleteAll();
    }

    public List<TiempoProfesor> getAll() {
        return tiempoProfesorDao.getAll();
    }

    public TiempoProfesor getById(int id) {
        return tiempoProfesorDao.getById(id);
    }

    public List<TiempoProfesor> getByProfesorOrigen(int idProfesor) {
        return tiempoProfesorDao.getByProfesorOrigen(idProfesor);
    }

    public List<TiempoProfesor> getByProfesorDestino(int idProfesor) {
        return tiempoProfesorDao.getByProfesorDestino(idProfesor);
    }

    public List<TiempoProfesor> getBySemana(int semana) {
        return tiempoProfesorDao.getBySemana(semana);
    }
}

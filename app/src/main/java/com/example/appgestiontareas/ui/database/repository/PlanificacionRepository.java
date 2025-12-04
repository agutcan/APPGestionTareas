package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.PlanificacionDao;
import com.example.appgestiontareas.ui.database.entidades.Planificacion;

import java.util.List;

public class PlanificacionRepository {

    private PlanificacionDao planificacionDao;

    public PlanificacionRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        planificacionDao = db.planificacionDao();
    }

    // ===== CRUD =====

    public long insert(Planificacion planificacion) {
        return planificacionDao.insert(planificacion);
    }

    public void update(Planificacion planificacion) {
        planificacionDao.update(planificacion);
    }

    public void delete(Planificacion planificacion) {
        planificacionDao.delete(planificacion);
    }

    public void deleteAll() {
        planificacionDao.deleteAll();
    }

    public List<Planificacion> getAll() {
        return planificacionDao.getAll();
    }

    public Planificacion getById(int id) {
        return planificacionDao.getById(id);
    }

    public List<Planificacion> getByAlumno(int idAlumno) {
        return planificacionDao.getByAlumno(idAlumno);
    }

    public List<Planificacion> getByTarea(int idTarea) {
        return planificacionDao.getByTarea(idTarea);
    }
}

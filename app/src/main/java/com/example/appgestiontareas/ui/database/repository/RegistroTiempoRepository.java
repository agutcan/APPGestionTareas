package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.RegistroTiempoDao;
import com.example.appgestiontareas.ui.database.entidades.RegistroTiempo;

import java.util.List;

public class RegistroTiempoRepository {

    private RegistroTiempoDao registroTiempoDao;

    public RegistroTiempoRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        registroTiempoDao = db.registroTiempoDao();
    }

    // ===== CRUD =====

    public long insert(RegistroTiempo registroTiempo) {
        return registroTiempoDao.insert(registroTiempo);
    }

    public void update(RegistroTiempo registroTiempo) {
        registroTiempoDao.update(registroTiempo);
    }

    public void delete(RegistroTiempo registroTiempo) {
        registroTiempoDao.delete(registroTiempo);
    }

    public void deleteAll() {
        registroTiempoDao.deleteAll();
    }

    public List<RegistroTiempo> getAll() {
        return registroTiempoDao.getAll();
    }

    public RegistroTiempo getById(int id) {
        return registroTiempoDao.getById(id);
    }

    public List<RegistroTiempo> getByAlumno(int idAlumno) {
        return registroTiempoDao.getByAlumno(idAlumno);
    }

    public List<RegistroTiempo> getByTarea(int idTarea) {
        return registroTiempoDao.getByTarea(idTarea);
    }
}

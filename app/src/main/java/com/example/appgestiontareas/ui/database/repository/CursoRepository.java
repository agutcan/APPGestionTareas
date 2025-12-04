package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.CursoDao;
import com.example.appgestiontareas.ui.database.entidades.Curso;

import java.util.List;

public class CursoRepository {

    private CursoDao cursoDao;

    public CursoRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        cursoDao = db.cursoDao();
    }

    // ===== CRUD =====

    public long insert(Curso curso) {
        return cursoDao.insert(curso);
    }

    public void update(Curso curso) {
        cursoDao.update(curso);
    }

    public void delete(Curso curso) {
        cursoDao.delete(curso);
    }

    public void deleteAll() {
        cursoDao.deleteAll();
    }

    public List<Curso> getAll() {
        return cursoDao.getAll();
    }

    public Curso getById(int id) {
        return cursoDao.getById(id);
    }
}

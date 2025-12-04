package com.example.appgestiontareas.ui.database.repository;

import android.content.Context;

import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

import java.util.List;

public class UsuarioRepository {

    private UsuarioDao usuarioDao;

    public UsuarioRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        usuarioDao = db.usuarioDao();
    }

    // ===== CRUD =====

    public long insert(Usuario usuario) {
        return usuarioDao.insert(usuario);
    }

    public void update(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    public void deleteAll() {
        usuarioDao.deleteAll();
    }

    public List<Usuario> getAll() {
        return usuarioDao.getAll();
    }

    public Usuario getById(int id) {
        return usuarioDao.getById(id);
    }

    public List<Usuario> getByRol(String rol) {
        return usuarioDao.getByRol(rol);
    }

    // ===== Métodos específicos de tiempo =====

    public void actualizarTiempo(int userId, int nuevoTiempo) {
        if (nuevoTiempo < 0) nuevoTiempo = 0; // evita tiempo negativo
        usuarioDao.actualizarTiempo(userId, nuevoTiempo);
    }

    public Integer obtenerTiempo(int userId) {
        Integer tiempo = usuarioDao.obtenerTiempo(userId);
        return tiempo != null ? tiempo : 0;
    }
}

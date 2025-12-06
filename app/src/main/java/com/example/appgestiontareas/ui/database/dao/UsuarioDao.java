package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
@Dao
public interface UsuarioDao {

    @Insert
    long insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Query("DELETE FROM usuario")
    void deleteAll();

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id = :id")
    Usuario getById(int id);

    @Query("SELECT * FROM usuario WHERE rol = :rol")
    List<Usuario> getByRol(String rol);

    @Query("UPDATE usuario SET tiempo = :nuevoTiempo WHERE id = :userId")
    void actualizarTiempo(int userId, int nuevoTiempo);

    @Query("SELECT tiempo FROM usuario WHERE id = :userId")
    Integer obtenerTiempo(int userId);

    @Query("SELECT id FROM usuario WHERE rol = 'profesor' AND id != :idExcluido")
    List<Integer> obtenerIdsProfesoresExcepto(int idExcluido);

}
package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.RegistroTiempo;

@Dao
public interface RegistroTiempoDao {

    @Insert
    long insert(RegistroTiempo registroTiempo);

    @Update
    void update(RegistroTiempo registroTiempo);

    @Delete
    void delete(RegistroTiempo registroTiempo);

    @Query("DELETE FROM registrotiempo")
    void deleteAll();

    @Query("SELECT * FROM registrotiempo")
    List<RegistroTiempo> getAll();

    @Query("SELECT * FROM registrotiempo WHERE id = :id")
    RegistroTiempo getById(int id);

    @Query("SELECT * FROM registrotiempo WHERE id_alumno = :idAlumno")
    List<RegistroTiempo> getByAlumno(int idAlumno);

    @Query("SELECT * FROM registrotiempo WHERE id_tarea = :idTarea")
    List<RegistroTiempo> getByTarea(int idTarea);
}
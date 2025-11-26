package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Planificacion;
@Dao
public interface PlanificacionDao {

    @Insert
    long insert(Planificacion planificacion);

    @Update
    void update(Planificacion planificacion);

    @Delete
    void delete(Planificacion planificacion);

    @Query("DELETE FROM planificacion")
    void deleteAll();

    @Query("SELECT * FROM planificacion")
    List<Planificacion> getAll();

    @Query("SELECT * FROM planificacion WHERE id = :id")
    Planificacion getById(int id);

    @Query("SELECT * FROM planificacion WHERE id_alumno = :idAlumno")
    List<Planificacion> getByAlumno(int idAlumno);

    @Query("SELECT * FROM planificacion WHERE id_tarea = :idTarea")
    List<Planificacion> getByTarea(int idTarea);
}

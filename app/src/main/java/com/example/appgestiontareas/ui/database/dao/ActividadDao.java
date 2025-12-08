package com.example.appgestiontareas.ui.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Actividad;

@Dao
public interface ActividadDao {

    @Insert
    long insert(Actividad actividad);

    @Update
    void update(Actividad actividad);

    @Delete
    void delete(Actividad actividad);

    @Query("DELETE FROM actividad")
    void deleteAll();

    @Query("SELECT * FROM actividad")
    List<Actividad> getAll();

    @Query("SELECT * FROM actividad WHERE id = :id")
    Actividad getById(int id);

//    @Query("SELECT * FROM actividad WHERE id_alumno = :idAlumno")
//    List<Actividad> getByAlumno(int idAlumno);

    @Query("SELECT * FROM actividad WHERE id_asignatura = :idAsignatura")
    List<Actividad> getByAsignatura(int idAsignatura);

    @Query("SELECT * FROM actividad WHERE tipo = :tipo")
    List<Actividad> getByTipo(String tipo);

    @Query("SELECT * FROM actividad ORDER BY fecha_entrega")
    List<Actividad> getAllOrderByFecha();

}

package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;

@Dao
public interface AsignaturaDao {

    @Insert
    long insert(Asignatura asignatura);

    @Update
    void update(Asignatura asignatura);

    @Delete
    void delete(Asignatura asignatura);

    @Query("DELETE FROM asignaturas")
    void deleteAll();

    @Query("SELECT * FROM asignaturas")
    List<Asignatura> getAll();

    @Query("SELECT * FROM asignaturas WHERE id = :id")
    Asignatura getById(int id);

    @Query("SELECT * FROM asignaturas WHERE id_curso = :idCurso")
    List<Asignatura> getByCurso(int idCurso);

    @Query("SELECT * FROM asignaturas WHERE id_profesor = :idProfesor LIMIT 1")
    Asignatura getAsignaturaPorProfesor(int idProfesor);
}
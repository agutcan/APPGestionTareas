package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Curso;

@Dao
public interface CursoDao {

    @Insert
    long insert(Curso curso);

    @Update
    void update(Curso curso);

    @Delete
    void delete(Curso curso);

    @Query("DELETE FROM cursos")
    void deleteAll();

    @Query("SELECT * FROM cursos")
    List<Curso> getAll();

    @Query("SELECT * FROM cursos WHERE id = :id")
    Curso getById(int id);
}
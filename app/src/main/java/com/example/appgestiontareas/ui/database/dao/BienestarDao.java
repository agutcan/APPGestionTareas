package com.example.appgestiontareas.ui.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.Bienestar;

@Dao
public interface BienestarDao {

    @Insert
    long insert(Bienestar bienestar);

    @Update
    void update(Bienestar bienestar);

    @Delete
    void delete(Bienestar bienestar);

    @Query("DELETE FROM bienestar")
    void deleteAll();

    @Query("SELECT * FROM bienestar")
    List<Bienestar> getAll();

    @Query("SELECT * FROM bienestar WHERE id = :id")
    Bienestar getById(int id);

    @Query("SELECT * FROM bienestar WHERE id_alumno = :idAlumno")
    List<Bienestar> getByAlumno(int idAlumno);
}

package com.example.appgestiontareas.ui.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;

@Dao
public interface TiempoProfesorDao {

    @Insert
    long insert(TiempoProfesor tiempoProfesor);

    @Update
    void update(TiempoProfesor tiempoProfesor);

    @Delete
    void delete(TiempoProfesor tiempoProfesor);

    @Query("DELETE FROM tiempoprofesor")
    void deleteAll();

    @Query("SELECT * FROM tiempoprofesor")
    List<TiempoProfesor> getAll();

    @Query("SELECT * FROM tiempoprofesor WHERE id = :id")
    TiempoProfesor getById(int id);

    @Query("SELECT * FROM tiempoprofesor WHERE id_profesor_origen = :idProfesor")
    List<TiempoProfesor> getByProfesorOrigen(int idProfesor);

    @Query("SELECT * FROM tiempoprofesor WHERE id_profesor_destino = :idProfesor")
    List<TiempoProfesor> getByProfesorDestino(int idProfesor);

    @Query("SELECT * FROM tiempoprofesor WHERE semana = :semana")
    List<TiempoProfesor> getBySemana(int semana);
}

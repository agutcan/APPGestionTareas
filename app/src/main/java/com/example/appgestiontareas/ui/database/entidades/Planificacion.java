package com.example.appgestiontareas.ui.database.entidades;
import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
@Entity(
        tableName = "planificacion",
        foreignKeys = {
                @ForeignKey(
                        entity = Actividad.class,
                        parentColumns = "id",
                        childColumns = "id_tarea",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_alumno",
                        onDelete = CASCADE
                )
        }
)
public class Planificacion {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_tarea;
    private int id_alumno;

    private String fecha_planificada;
    private int segundos_planificados;

    // Constructor vacío requerido por Room
    public Planificacion() {}

    // Constructor con parámetros útil para tus ejemplos
    public Planificacion(int id_alumno, int id_tarea, String fecha_planificada, int segundos_planificados) {
        this.id_alumno = id_alumno;
        this.id_tarea = id_tarea;
        this.fecha_planificada = fecha_planificada;
        this.segundos_planificados = segundos_planificados;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getFecha_planificada() {
        return fecha_planificada;
    }

    public void setFecha_planificada(String fecha_planificada) {
        this.fecha_planificada = fecha_planificada;
    }

    public int getSegundos_planificados() {
        return segundos_planificados;
    }

    public void setSegundos_planificados(int segundos_planificados) {
        this.segundos_planificados = segundos_planificados;
    }
}
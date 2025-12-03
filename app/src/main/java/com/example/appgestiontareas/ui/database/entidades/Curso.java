package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cursos")
public class Curso {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private int segundos_semanales_max;
    private int max_examenes_semana;

    // Constructor vacío requerido por Room
    public Curso() {}

    // Constructor con parámetros útil para tus ejemplos
    public Curso(String nombre, int segundos_semanales_max, int max_examenes_semana) {
        this.nombre = nombre;
        this.segundos_semanales_max = segundos_semanales_max;
        this.max_examenes_semana = max_examenes_semana;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSegundos_semanales_max() {
        return segundos_semanales_max;
    }

    public void setSegundos_semanales_max(int segundos_semanales_max) {
        this.segundos_semanales_max = segundos_semanales_max;
    }

    public int getMax_examenes_semana() {
        return max_examenes_semana;
    }

    public void setMax_examenes_semana(int max_examenes_semana) {
        this.max_examenes_semana = max_examenes_semana;
    }
}

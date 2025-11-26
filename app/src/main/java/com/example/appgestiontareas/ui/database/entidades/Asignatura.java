package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(
        tableName = "asignaturas",
        foreignKeys = @ForeignKey(
                entity = Curso.class,
                parentColumns = "id",
                childColumns = "id_curso",
                onDelete = ForeignKey.CASCADE
        )
)
public class Asignatura {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_curso;
    private String nombre;
    private int horas_semanales;

    // Constructor vacío requerido por Room
    public Asignatura() {}

    // Constructor con parámetros útil para tus ejemplos
    public Asignatura(int id_curso, String nombre, int horas_semanales) {
        this.id_curso = id_curso;
        this.nombre = nombre;
        this.horas_semanales = horas_semanales;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras_semanales() {
        return horas_semanales;
    }

    public void setHoras_semanales(int horas_semanales) {
        this.horas_semanales = horas_semanales;
    }
}
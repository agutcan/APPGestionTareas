package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
@Entity(
        tableName = "bienestar",
        foreignKeys = @ForeignKey(
                entity = Usuario.class,
                parentColumns = "id",
                childColumns = "id_alumno",
                onDelete = ForeignKey.CASCADE
        )
)
public class Bienestar {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_alumno;
    private String fecha;
    private String estado_animo;
    private int horas_sueno;
    private String notas;

    // Constructor vacío requerido por Room
    public Bienestar() {}

    // Constructor con parámetros útil para tus ejemplos
    public Bienestar(int id_alumno, String fecha, String estado_animo, int horas_sueno, String notas) {
        this.id_alumno = id_alumno;
        this.fecha = fecha;
        this.estado_animo = estado_animo;
        this.horas_sueno = horas_sueno;
        this.notas = notas;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado_animo() {
        return estado_animo;
    }

    public void setEstado_animo(String estado_animo) {
        this.estado_animo = estado_animo;
    }

    public int getHoras_sueno() {
        return horas_sueno;
    }

    public void setHoras_sueno(int horas_sueno) {
        this.horas_sueno = horas_sueno;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
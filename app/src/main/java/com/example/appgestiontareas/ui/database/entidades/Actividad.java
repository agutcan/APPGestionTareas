package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
@Entity(
        tableName = "actividad",
        foreignKeys = {
                @ForeignKey(
                        entity = Asignatura.class,
                        parentColumns = "id",
                        childColumns = "id_asignatura",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_alumno",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Actividad {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_asignatura;
    private int id_alumno;

    private String titulo;
    private String descripcion;
    private int minutos_estimados;
    private String fecha_entrega;
    private int peso_estimado;
    private int creado_por;

    private String tipo;   // <--- Nuevo campo: "tarea" o "examen"

    // Constructor vacío requerido por Room
    public Actividad() {}

    // Constructor con parámetros útil para tus ejemplos
    public Actividad(int id_asignatura, int id_alumno, String titulo, String tipo, String fecha_entrega) {
        this.id_asignatura = id_asignatura;
        this.id_alumno = id_alumno;
        this.titulo = titulo;
        this.tipo = tipo;
        this.fecha_entrega = fecha_entrega;
    }

    // --- Getters y Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMinutos_estimados() {
        return minutos_estimados;
    }

    public void setMinutos_estimados(int minutos_estimados) {
        this.minutos_estimados = minutos_estimados;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public int getPeso_estimado() {
        return peso_estimado;
    }

    public void setPeso_estimado(int peso_estimado) {
        this.peso_estimado = peso_estimado;
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
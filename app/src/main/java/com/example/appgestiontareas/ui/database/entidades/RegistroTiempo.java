package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
@Entity(
        tableName = "registrotiempo",
        foreignKeys = {
                @ForeignKey(
                        entity = Actividad.class,
                        parentColumns = "id",
                        childColumns = "id_tarea",
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
public class RegistroTiempo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_tarea;
    private int id_alumno;

    private int segundos_dedicados;
    private String fecha;

    // Constructor vacío requerido por Room
    public RegistroTiempo() {}

    // Constructor con parámetros útil para tus ejemplos
    public RegistroTiempo(int id_alumno, int id_tarea, int segundos_dedicados, String fecha) {
        this.id_alumno = id_alumno;
        this.id_tarea = id_tarea;
        this.segundos_dedicados = segundos_dedicados;
        this.fecha = fecha;
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

    public int getSegundos_dedicados() {
        return segundos_dedicados;
    }

    public void setSegundos_dedicados(int segundos_dedicados) {
        this.segundos_dedicados = segundos_dedicados;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

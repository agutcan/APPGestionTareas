package com.example.appgestiontareas.ui.database.entidades;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
@Entity(
        tableName = "tiempoprofesor",
        foreignKeys = {
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_profesor_origen",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_profesor_destino",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class TiempoProfesor {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int id_profesor_origen;
    private int id_profesor_destino;

    private int segundos;
    private String tipo; // prestar / regalar
    private int semana;

    // Constructor vacío requerido por Room
    public TiempoProfesor() {}

    // Constructor con parámetros útil para ejemplos
    public TiempoProfesor(int id_profesor_origen, int id_profesor_destino, int segundos, String tipo, int semana) {
        this.id_profesor_origen = id_profesor_origen;
        this.id_profesor_destino = id_profesor_destino;
        this.segundos = segundos;
        this.tipo = tipo;
        this.semana = semana;
    }

// Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_profesor_origen() {
        return id_profesor_origen;
    }

    public void setId_profesor_origen(int id_profesor_origen) {
        this.id_profesor_origen = id_profesor_origen;
    }

    public int getId_profesor_destino() {
        return id_profesor_destino;
    }

    public void setId_profesor_destino(int id_profesor_destino) {
        this.id_profesor_destino = id_profesor_destino;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }
}
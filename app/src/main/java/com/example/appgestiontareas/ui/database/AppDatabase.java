package com.example.appgestiontareas.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// DAOs
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.dao.CursoDao;
import com.example.appgestiontareas.ui.database.dao.AsignaturaDao;
import com.example.appgestiontareas.ui.database.dao.ActividadDao;
import com.example.appgestiontareas.ui.database.dao.RegistroTiempoDao;
import com.example.appgestiontareas.ui.database.dao.PlanificacionDao;
import com.example.appgestiontareas.ui.database.dao.BienestarDao;
import com.example.appgestiontareas.ui.database.dao.TiempoProfesorDao;

// ENTIDADES
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.database.entidades.Curso;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.RegistroTiempo;
import com.example.appgestiontareas.ui.database.entidades.Planificacion;
import com.example.appgestiontareas.ui.database.entidades.Bienestar;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;

@Database(
        entities = {
                Usuario.class,
                Curso.class,
                Asignatura.class,
                Actividad.class,
                RegistroTiempo.class,
                Planificacion.class,
                Bienestar.class,
                TiempoProfesor.class
        },
        version = 11,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    // -------- DAOS --------
    public abstract UsuarioDao usuarioDao();
    public abstract CursoDao cursoDao();
    public abstract AsignaturaDao asignaturaDao();
    public abstract ActividadDao actividadDao();
    public abstract RegistroTiempoDao registroTiempoDao();
    public abstract PlanificacionDao planificacionDao();
    public abstract BienestarDao bienestarDao();
    public abstract TiempoProfesorDao tiempoProfesorDao();


    // -------- SINGLETON --------
    private static volatile AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_gestion_tareas_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

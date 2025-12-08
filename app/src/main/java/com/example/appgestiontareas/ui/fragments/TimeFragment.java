package com.example.appgestiontareas.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.activities.SettingsActivity;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.TiempoProfesorDao;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.entidades.TiempoProfesor;
import com.example.appgestiontareas.ui.database.entidades.Usuario;
import com.example.appgestiontareas.ui.utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeFragment extends Fragment {

    private RadioButton rbDar, rbLiberar;
    private EditText etTiempo;
    private Spinner spProfesor;
    private Button btnEnviar;
    private TextView tvTiempoLibre;

    private final static String FICHERO = "USER_PREFS";
    private Map<String, Integer> mapaProfesores;
    private int idLogueado;

    public TimeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rbDar = view.findViewById(R.id.rbDar);
        rbLiberar = view.findViewById(R.id.rbLiberar);
        spProfesor = view.findViewById(R.id.spProfesor);
        etTiempo = view.findViewById(R.id.etTiempo);
        btnEnviar = view.findViewById(R.id.btnEnviar);
        tvTiempoLibre = view.findViewById(R.id.tvTiempoLibre);

        mapaProfesores = new HashMap<>();

        idLogueado = requireActivity()
                .getSharedPreferences(FICHERO, Context.MODE_PRIVATE)
                .getInt("user_id", -1);

        cargarProfesores();
        mostrarTiempoLogueado();

        ImageButton btnAjustes = view.findViewById(R.id.ajustesBtn2);
        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });

        btnEnviar.setOnClickListener(v -> procesarFormulario());
    }

    // 🔹 Cargar profesores excepto el logueado
    private void cargarProfesores() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {

            AppDatabase db = AppDatabase.getInstance(getContext());
            UsuarioDao usuarioDao = db.usuarioDao();
            List<Usuario> profesores = usuarioDao.getByRol("profesor");

            List<String> nombres = new ArrayList<>();
            nombres.add("Sin profesor");

            for (Usuario p : profesores) {
                mapaProfesores.put(p.getNombre(), p.getId());
                if (p.getId() != idLogueado) {
                    nombres.add(p.getNombre());
                }
            }

            requireActivity().runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        nombres
                );
                spProfesor.setAdapter(adapter);
            });
        });
    }

    // 🔹 Mostrar SIEMPRE el tiempo del profesor logueado
    private void mostrarTiempoLogueado() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {

            AppDatabase db = AppDatabase.getInstance(getContext());
            UsuarioDao usuarioDao = db.usuarioDao();

            int tiempo = usuarioDao.obtenerTiempo(idLogueado);

            requireActivity().runOnUiThread(() ->
                    tvTiempoLibre.setText("Tu tiempo libre actual: " + utils.secondsToHours(tiempo) + " h")
            );
        });
    }


    private void procesarFormulario() {

        String accion = rbDar.isChecked() ? "Dar" : rbLiberar.isChecked() ? "Liberar" : "";
        String profesorSeleccionado = spProfesor.getSelectedItem().toString();
        String tiempoStr = etTiempo.getText().toString().trim();

        if (accion.isEmpty()) {
            Toast.makeText(getContext(), "Selecciona una acción", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tiempoStr.isEmpty()) {
            Toast.makeText(getContext(), "Introduce tiempo", Toast.LENGTH_SHORT).show();
            return;
        }

        int tiempoInt = utils.hoursToSeconds(Integer.parseInt(tiempoStr));

        if (accion.equals("Dar") && profesorSeleccionado.equals("Sin profesor")) {
            Toast.makeText(getContext(),
                    "Debes seleccionar un profesor al que dar tiempo",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (accion.equals("Liberar") && !profesorSeleccionado.equals("Sin profesor")) {
            Toast.makeText(getContext(),
                    "No puedes seleccionar profesor al LIBERAR tiempo",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {

            AppDatabase db = AppDatabase.getInstance(getContext());
            UsuarioDao usuarioDao = db.usuarioDao();
            TiempoProfesorDao tiempoDao = db.tiempoProfesorDao();

            int profesorSeleccionadoId =
                    profesorSeleccionado.equals("Sin profesor") ? -1 :
                            mapaProfesores.get(profesorSeleccionado);

            int tiempoActual = usuarioDao.obtenerTiempo(idLogueado);

            if (tiempoInt > tiempoActual) {
                Log.d("TransferenciaTiempo", "Error: tiempo insuficiente. Tiempo disponible: " + tiempoActual + ", intentado dar: " + tiempoInt);
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(),
                                "No tienes suficiente tiempo para dar",
                                Toast.LENGTH_SHORT).show()
                );
                return;
            }

            if (accion.equals("Dar")) {

                int tiempoOrigen = usuarioDao.obtenerTiempo(idLogueado);
                int tiempoDestino = usuarioDao.obtenerTiempo(profesorSeleccionadoId);

                Log.d("TransferenciaTiempo", "Intentando dar " + tiempoInt + " segundos del profesor " + idLogueado + " al profesor " + profesorSeleccionadoId);
                Log.d("TransferenciaTiempo", "Tiempo actual -> Origen: " + tiempoOrigen + ", Destino: " + tiempoDestino);

                int nuevoTiempoOrigen = tiempoOrigen - tiempoInt;
                int nuevoTiempoDestino = tiempoDestino + tiempoInt;

                if (nuevoTiempoOrigen < 0) {
                    Log.d("TransferenciaTiempo", "Error: tiempo insuficiente. Tiempo disponible: " + tiempoOrigen + ", intentado dar: " + tiempoInt);
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(),
                                    "No tienes suficiente tiempo para dar",
                                    Toast.LENGTH_SHORT).show()
                    );
                    return;
                }

                usuarioDao.actualizarTiempo(idLogueado, nuevoTiempoOrigen);
                usuarioDao.actualizarTiempo(profesorSeleccionadoId, nuevoTiempoDestino);

                Log.d("TransferenciaTiempo", "Tiempo actualizado -> Origen: " + nuevoTiempoOrigen + ", Destino: " + nuevoTiempoDestino);

                tiempoDao.insert(new TiempoProfesor(
                        idLogueado,
                        profesorSeleccionadoId,
                        tiempoInt,
                        accion,
                        utils.obtenerSemanaActual()
                ));

                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(),
                                "Tiempo dado correctamente",
                                Toast.LENGTH_SHORT).show()
                );
                Log.d("TransferenciaTiempo", "Registro de acción insertado en TiempoProfesor para " + tiempoInt + " minutos de " + idLogueado + " a " + profesorSeleccionadoId);
            }

            if (accion.equals("Liberar")) {
                tiempoActual = usuarioDao.obtenerTiempo(idLogueado);
                int nuevoTiempo = tiempoActual - tiempoInt; // Restar tiempo al profesor logueado
                usuarioDao.actualizarTiempo(idLogueado, nuevoTiempo);

                Log.d("RedistribucionTiempo", "Profesor " + idLogueado + " liberó " + tiempoInt + " segundos. Tiempo restante: " + nuevoTiempo);

                // Obtener todos los demás profesores
                List<Integer> otrosProfesores = usuarioDao.obtenerIdsProfesoresExcepto(idLogueado);
                if (!otrosProfesores.isEmpty()) {
                    int tiempoPorProfesor = tiempoInt / otrosProfesores.size(); // Dividir entre los restantes

                    for (int profesorId : otrosProfesores) {
                        int tiempoProfesor = usuarioDao.obtenerTiempo(profesorId);
                        Log.d("RedistribucionTiempo", "Tiempo por profesor restante: " + tiempoPorProfesor);
                        usuarioDao.actualizarTiempo(profesorId, tiempoProfesor + tiempoPorProfesor);

                        // Registrar acción para cada profesor que recibe tiempo
                        tiempoDao.insert(new TiempoProfesor(
                                profesorId,
                                idLogueado,
                                tiempoPorProfesor,
                                "Liberar",
                                utils.obtenerSemanaActual()
                        ));
                        Log.d("RedistribucionTiempo", "Profesor " + profesorId + " recibió " + tiempoPorProfesor + " segundos. Tiempo total ahora: " + (tiempoProfesor + tiempoPorProfesor));
                    }
                }

                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(),
                                "Tiempo liberado y redistribuido correctamente",
                                Toast.LENGTH_SHORT).show()
                );
            }

            // 🔹 Refresca SIEMPRE el tiempo del profesor LOGUEADO
            mostrarTiempoLogueado();
        });
    }
}

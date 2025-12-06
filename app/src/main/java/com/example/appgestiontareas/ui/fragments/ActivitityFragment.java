package com.example.appgestiontareas.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActivitityFragment extends Fragment {

    private RadioButton rbDar, rbLiberar;
    private EditText etTiempo;
    private Spinner spProfesor;
    private Button btnEnviar;
    private final static String FICHERO = "USER_PREFS";


    public ActivitityFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflamos el layout del fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Referencias UI
        rbDar = view.findViewById(R.id.rbDar);
        rbLiberar = view.findViewById(R.id.rbLiberar);
        spProfesor = view.findViewById(R.id.spProfesor);
        etTiempo = view.findViewById(R.id.etTiempo);
        btnEnviar = view.findViewById(R.id.btnEnviar);

        int idLogueado = requireActivity()
                .getSharedPreferences(FICHERO, Context.MODE_PRIVATE)
                .getInt("user_id", -1);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getContext());
            List<Usuario> profesores = db.usuarioDao().getByRol("profesor");

            List<String> nombres = new ArrayList<>();
            nombres.add("Sin profesor"); // opción opcional
            for (Usuario p : profesores) {
                if (p.getId() != idLogueado) {   // OCULTAR PROFESOR LOGUEADO
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

        // Listener del botón
        btnEnviar.setOnClickListener(v -> procesarFormulario());
    }

    private void procesarFormulario() {
        String accion = rbDar.isChecked() ? "Dar" : rbLiberar.isChecked() ? "Liberar" : "";
        String profesorSeleccionado = spProfesor.getSelectedItem().toString();
        String tiempo = etTiempo.getText().toString().trim();

        if (accion.isEmpty()) {
            Toast.makeText(getContext(), "Selecciona una acción", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tiempo.isEmpty()) {
            Toast.makeText(getContext(), "Introduce tiempo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí puedes llamar al repositorio o a la activity para aplicar la lógica
        Toast.makeText(
                getContext(),
                "Acción: " + accion + "\nProfesor: " + profesorSeleccionado + "\nTiempo: " + tiempo,
                Toast.LENGTH_LONG
        ).show();
    }
}

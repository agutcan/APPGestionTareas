package com.example.appgestiontareas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;

public class ActivitityFragment extends Fragment {

    private RadioButton rbDar, rbLiberar;
    private EditText etProfesor, etTiempo;
    private Button btnEnviar;

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
        etProfesor = view.findViewById(R.id.etProfesor);
        etTiempo = view.findViewById(R.id.etTiempo);
        btnEnviar = view.findViewById(R.id.btnEnviar);

        // Listener del botón
        btnEnviar.setOnClickListener(v -> procesarFormulario());
    }

    private void procesarFormulario() {
        String accion = rbDar.isChecked() ? "Dar" : rbLiberar.isChecked() ? "Liberar" : "";
        String profesor = etProfesor.getText().toString().trim();
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
                "Acción: " + accion + "\nProfesor: " + profesor + "\nTiempo: " + tiempo,
                Toast.LENGTH_LONG
        ).show();
    }
}

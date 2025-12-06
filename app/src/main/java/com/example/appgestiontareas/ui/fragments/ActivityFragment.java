package com.example.appgestiontareas.ui.fragments;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;

public class ActivityFragment extends Fragment {

    public ActivityFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText inputAsignacion = view.findViewById(R.id.inputAsignacion);
        EditText inputEntrega = view.findViewById(R.id.inputEntrega);

        Calendar calendario = Calendar.getInstance();

        // ----------- FECHA ASIGNACIÓN -------------
        inputAsignacion.setOnClickListener(v -> {
            int año = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    getContext(),
                    (view1, year, month, dayOfMonth) -> {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        inputAsignacion.setText(fecha);
                    },
                    año, mes, dia
            );
            datePicker.show();
        });

        // ----------- FECHA ENTREGA -------------
        inputEntrega.setOnClickListener(v -> {
            int año = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    getContext(),
                    (view12, year, month, dayOfMonth) -> {
                        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                        inputEntrega.setText(fecha);
                    },
                    año, mes, dia
            );
            datePicker.show();
        });

        Button btnCrear = view.findViewById(R.id.btnCrear);
        RadioGroup grupoTipo = view.findViewById(R.id.grupoTipo);
        EditText inputDescripcion = view.findViewById(R.id.inputDescripcion);

        btnCrear.setOnClickListener(v -> {

            // 1. Tipo seleccionado (Tarea o Examen)
            int idSeleccionado = grupoTipo.getCheckedRadioButtonId();
            String tipo = "";
            if (idSeleccionado != -1) {
                tipo = ((RadioButton) view.findViewById(idSeleccionado)).getText().toString().toLowerCase();
            }

            // 2. Fechas
            String fechaAsignacion = inputAsignacion.getText().toString().trim();
            String fechaEntrega = inputEntrega.getText().toString().trim();

            // 3. Descripción
            String descripcion = inputDescripcion.getText().toString().trim();

            // -------- DEBUG - Mostrar los datos --------
            Log.d("CREAR_ACTIVIDAD", "Tipo: " + tipo);
            Log.d("CREAR_ACTIVIDAD", "Asignación: " + fechaAsignacion);
            Log.d("CREAR_ACTIVIDAD", "Entrega: " + fechaEntrega);
            Log.d("CREAR_ACTIVIDAD", "Descripción: " + descripcion);

            // Aquí ya puedes guardar en BD, enviar a API, etc.
        });




    }
}
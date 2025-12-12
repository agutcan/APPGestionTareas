package com.example.appgestiontareas.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestiontareas.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WelfareActivity extends AppCompatActivity {
    private Spinner spinnerEstado;
    private EditText editHoras, editNotas;
    private Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welfare);


        spinnerEstado = findViewById(R.id.spinnerEstado);
        editHoras = findViewById(R.id.editHoras);
        editNotas = findViewById(R.id.editNotas);
        btnEnviar = findViewById(R.id.btnEnviar);

        String[] estados = {"Feliz", "Triste", "Cansado", "Motivado", "Estresado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, estados
        );
        spinnerEstado.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> registrarAutoEvaluacion());

        ImageButton btnAjustes = findViewById(R.id.ajustesBtn2);
        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });


    }

    private void registrarAutoEvaluacion() {
        String estado = spinnerEstado.getSelectedItem().toString();
        String horasSueno = editHoras.getText().toString();
        String notas = editNotas.getText().toString();

        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .format(new Date());

        Log.d("WELFARE", "Estado de ánimo: " + estado);
        Log.d("WELFARE", "Horas de sueño: " + horasSueno);
        Log.d("WELFARE", "Nota: " + notas);
        Log.d("WELFARE", "Fecha del registro: " + fecha);

        android.widget.Toast.makeText(this, "Autoevaluación registrada", Toast.LENGTH_SHORT).show();

    }
}
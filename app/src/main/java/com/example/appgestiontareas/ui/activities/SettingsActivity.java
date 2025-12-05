package com.example.appgestiontareas.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appgestiontareas.R;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends AppCompatActivity {

    EditText correoBtn, cambiarContrasenaBtn;
    MaterialSwitch modoClaroBtn, notificacionesBtn;
    Spinner idiomaSpn;
    Button guardarBtn, cerrarSesionBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correoBtn = findViewById(R.id.correoBtn);
        cambiarContrasenaBtn = findViewById(R.id.cambiarContrasenaBtn);
        modoClaroBtn = findViewById(R.id.modoClaroBtn);
        notificacionesBtn = findViewById(R.id.notificacionesBtn);
        idiomaSpn = findViewById(R.id.idiomaSpn);
        guardarBtn = findViewById(R.id.guardarBtn);
        cerrarSesionBtn = findViewById(R.id.cerrarSesionBtn);

    }
}
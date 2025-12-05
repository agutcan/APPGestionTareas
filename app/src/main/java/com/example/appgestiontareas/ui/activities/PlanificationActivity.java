package com.example.appgestiontareas.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appgestiontareas.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class PlanificationActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rbTarea, rbExamen;

    MaterialCalendarView calendario;

    Button crearActividadBtn, darTiempoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroupTipo);
        rbTarea = findViewById(R.id.rbTarea);
        rbExamen = findViewById(R.id.rbExamen);

        calendario = findViewById(R.id.calendario);

        crearActividadBtn = findViewById(R.id.crearActividadBtn);
        darTiempoBtn = findViewById(R.id.darTiempoBtn);

        final RadioButton[] lastChecked = {null};

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = group.findViewById(checkedId);

            if (selected != null) {
                if (selected == lastChecked[0]) {
                    // Deseleccionar si se hace clic sobre la opción ya marcada
                    group.clearCheck();
                    lastChecked[0] = null;
                } else {
                    lastChecked[0] = selected;
                }
            }
        });
    }

    private void navegarAjustes() {
        Intent i = new Intent();

    }

}
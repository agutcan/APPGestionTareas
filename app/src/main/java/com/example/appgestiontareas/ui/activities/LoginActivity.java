package com.example.appgestiontareas.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.ejemplos.DatabaseEjemplo;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseEjemplo.ejecutar(this);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validaciones básicas
            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Introduce correo y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            checkLogin(email, password);
        });
    }

    private void checkLogin(String email, String password) {
        // andrea@email.com    1234

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            runOnUiThread(this::navegarHome);
//            AppDatabase db = AppDatabase.getInstance(this);
//            List<Usuario> usuarios = db.usuarioDao().getAll();
//            for (Usuario u : usuarios) {
//                Log.d("LOGIN_TEST", "Usuario en DB: " + u.getCorreo() + " / " + u.getContrasena());
//                if (u.getCorreo().trim().equalsIgnoreCase(email) && u.getContrasena().trim().equals(password)) {
//                    // Usuario correcto: llama al método que abre MainActivity
//                    runOnUiThread(this::navegarHome);
//                    return; // Salimos del bucle
//                }
//            }
//
//            // Usuario incorrecto
//            runOnUiThread(() ->
//                    Toast.makeText(LoginActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
//            );
        });
    }

    private void navegarHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}


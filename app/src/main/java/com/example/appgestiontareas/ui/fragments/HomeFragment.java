package com.example.appgestiontareas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

public class HomeFragment extends Fragment {

    TextView texto;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        texto = view.findViewById(R.id.text_home2);

        AppDatabase db = AppDatabase.getInstance(requireActivity().getApplicationContext());
        UsuarioDao usuarioDao = db.usuarioDao();

        // Hilo para no bloquear la UI
        new Thread(() -> {
            // Obtenemos un usuario, por ejemplo el primero de la lista
            Usuario usuario = null;
            if (!usuarioDao.getAll().isEmpty()) {
                usuario = usuarioDao.getAll().get(0);
            }

            Usuario finalUsuario = usuario;

            // Volvemos al hilo principal para actualizar la UI
            requireActivity().runOnUiThread(() -> {
                if (finalUsuario != null) {
                    texto.setText("Usuario: " + finalUsuario.getNombre() +
                            "\nTiempo: " + (finalUsuario.getTiempo() != null ? finalUsuario.getTiempo() : 0));
                } else {
                    texto.setText("No hay usuarios en la base de datos.");
                }
            });
        }).start();
    }



}


package com.example.appgestiontareas.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.activities.Welfare;

public class FollowUpFragment extends Fragment {

    public FollowUpFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follow_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Button btnHorario = view.findViewById(R.id.btnHorario);
        btnHorario.setOnClickListener(v -> {
            Fragment nuevoFragment = new ScheduleFragment();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, nuevoFragment)
                    .addToBackStack(null)
                    .commit();
        });

        Button btnBienestar = view.findViewById(R.id.btnBienestar);

        btnBienestar.setOnClickListener(v -> {
            // Aquí creamos el Intent para iniciar la Activity
            Intent intent = new Intent(getActivity(), Welfare.class);
            startActivity(intent);  // Lanzamos la Activity
        });
    }
}


package com.example.appgestiontareas.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.entidades.Asignatura;

import java.util.HashMap;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.AsignaturaViewHolder> {
    private List<Asignatura> listaAsignaturas;
    private HashMap<Integer, String> mapaProfesores;

    public HorarioAdapter(List<Asignatura> listaAsignaturas, HashMap<Integer, String> mapaProfesores) {
        this.listaAsignaturas = listaAsignaturas;
        this.mapaProfesores = mapaProfesores;
    }

    @NonNull
    @Override
    public AsignaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horario, parent, false);
        return new AsignaturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaViewHolder holder, int position) {
        Asignatura asignatura = listaAsignaturas.get(position);

        holder.txtAsignatura.setText(asignatura.getNombre());

        String nombreProfesor = mapaProfesores.get(asignatura.getId_profesor());
        holder.txtProfesor.setText("Profesor: " + (nombreProfesor != null ? nombreProfesor : "Desconocido"));

        // Hora ficticia
        holder.txtHora.setText("Hora: " + (8 + position) + ":00 - " + (9 + position) + ":00");
    }

    @Override
    public int getItemCount() {
        return listaAsignaturas.size();
    }

    public void updateData(List<Asignatura> nuevaLista) {
        listaAsignaturas.clear();
        listaAsignaturas.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    static class AsignaturaViewHolder extends RecyclerView.ViewHolder {
        TextView txtAsignatura, txtProfesor, txtHora;

        public AsignaturaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAsignatura = itemView.findViewById(R.id.txtAsignatura);
            txtProfesor = itemView.findViewById(R.id.txtProfesor);
            txtHora = itemView.findViewById(R.id.txtHora);
        }
    }
}

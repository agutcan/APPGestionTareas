package com.example.appgestiontareas.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.entidades.Actividad;

import java.util.List;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.VH> {

    private final List<Actividad> lista;

    public ActividadAdapter(List<Actividad> lista) {
        this.lista = lista;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvTipo, tvFecha;

        VH(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividad, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Actividad a = lista.get(position);
        holder.tvTitulo.setText(a.getTitulo());
        holder.tvTipo.setText(a.getTipo());
        holder.tvFecha.setText(a.getFecha_entrega());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

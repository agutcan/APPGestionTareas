package com.example.appgestiontareas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestiontareas.R;
import com.example.appgestiontareas.ui.database.AppDatabase;
import com.example.appgestiontareas.ui.database.dao.UsuarioDao;
import com.example.appgestiontareas.ui.database.entidades.Actividad;
import com.example.appgestiontareas.ui.database.entidades.Usuario;

import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AppDatabase db = AppDatabase.getInstance(requireContext());
        new Thread(() -> {
            List<Actividad> actividades = db.actividadDao().getAll();

            requireActivity().runOnUiThread(() -> {
                recyclerView.setAdapter(new ActividadAdapter(actividades));
            });
        }).start();

    }



    private class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.VH> {

        List<Actividad> lista;
        ActividadAdapter(List<Actividad> lista) { this.lista = lista; }

        class VH extends RecyclerView.ViewHolder {
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividad, parent, false);
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
        public int getItemCount() { return lista.size(); }
    }


}


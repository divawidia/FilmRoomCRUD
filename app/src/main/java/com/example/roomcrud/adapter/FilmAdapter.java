package com.example.roomcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomcrud.R;
import com.example.roomcrud.database.entitas.Film;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewAdapter> {
    private List<Film> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public FilmAdapter(Context context, List<Film> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_film, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.judul.setText(list.get(position).judul);
        holder.tahun.setText(list.get(position).tahun);
        holder.durasi.setText(list.get(position).durasi);
        holder.pemeran.setText(list.get(position).pemeran);
        holder.sinopsis.setText(list.get(position).sinopsis);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView judul, tahun, durasi, pemeran, sinopsis;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            tahun = itemView.findViewById(R.id.tahun);
            durasi = itemView.findViewById(R.id.durasi);
            pemeran = itemView.findViewById(R.id.pemeran);
            sinopsis = itemView.findViewById(R.id.sinopsis);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
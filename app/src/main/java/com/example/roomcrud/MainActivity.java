package com.example.roomcrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.roomcrud.adapter.FilmAdapter;
import com.example.roomcrud.database.AppDatabase;
import com.example.roomcrud.database.entitas.Film;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private FilmAdapter filmAdapter;
    private List<Film> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        btnTambah = findViewById(R.id.btn_tambah);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.filmDao().getAll());
        filmAdapter = new FilmAdapter(getApplicationContext(), list);
        filmAdapter.setDialog(new FilmAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                                intent.putExtra("id", list.get(position).id);
                                startActivity(intent);
                                break;
                            case 1:
                                Film user = list.get(position);
                                database.filmDao().delete(user);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(filmAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.filmDao().getAll());
        filmAdapter.notifyDataSetChanged();
    }
}
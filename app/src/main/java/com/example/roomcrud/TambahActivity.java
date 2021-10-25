package com.example.roomcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomcrud.database.AppDatabase;
import com.example.roomcrud.database.entitas.Film;

public class TambahActivity extends AppCompatActivity {
    private EditText editJudul, editTahun, editDurasi, editPemeran, editSinopsis;
    private Button btnSave;
    private AppDatabase database;
    private int id = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        editJudul = findViewById(R.id.edit_judul);
        editTahun = findViewById(R.id.edit_tahun);
        editDurasi = findViewById(R.id.edit_durasi);
        editPemeran = findViewById(R.id.edit_pemeran);
        editSinopsis = findViewById(R.id.edit_sinopsis);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id>0){
            isEdit = true;
            Film film = database.filmDao().get(id);
            editJudul.setText(film.judul);
            editTahun.setText(film.tahun);
            editDurasi.setText(film.durasi);
            editSinopsis.setText(film.sinopsis);
            editPemeran.setText(film.pemeran);
        }else{
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.filmDao().update(id, editJudul.getText().toString(), editTahun.getText().toString(), editDurasi.getText().toString(), editPemeran.getText().toString(), editSinopsis.getText().toString());
                }else{
                    database.filmDao().insertAll(editJudul.getText().toString(), editTahun.getText().toString(), editDurasi.getText().toString(), editPemeran.getText().toString(), editSinopsis.getText().toString());
                }
                finish();
            }
        });
    }
}
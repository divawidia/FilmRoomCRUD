package com.example.roomcrud.database.entitas;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Film {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "judul")
    public String judul;
    public String tahun;
    public String durasi;
    public String pemeran;
    public String sinopsis;
}

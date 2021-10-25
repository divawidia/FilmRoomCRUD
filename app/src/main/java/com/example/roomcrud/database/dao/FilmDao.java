package com.example.roomcrud.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomcrud.database.entitas.Film;

import java.util.List;

@Dao
public interface FilmDao {
    @Query("SELECT * FROM film")
    List<Film> getAll();

    @Query("INSERT INTO film (judul, tahun, durasi, pemeran, sinopsis) VALUES(:judul, :tahun, :durasi, :pemeran, :sinopsis)")
    void insertAll(String judul, String tahun, String durasi, String pemeran, String sinopsis);

    @Query("UPDATE film SET judul=:judul, tahun=:tahun, durasi=:durasi, pemeran=:pemeran, sinopsis=:sinopsis WHERE id=:id")
    void update(int id, String judul, String tahun, String durasi, String pemeran, String sinopsis);

    @Query("SELECT * FROM film WHERE id=:id")
    Film get(int id);

    @Delete
    void delete(Film film);
}

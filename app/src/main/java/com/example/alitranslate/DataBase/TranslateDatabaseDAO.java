package com.example.alitranslate.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alitranslate.Model.Words;

import java.util.List;

@Dao
public interface TranslateDatabaseDAO {
    @Insert
    void insertWords(Words words);

    @Update
    void updateWords(Words words);

    @Delete
    void deleteWords(Words words);

    @Query("SELECT * FROM translatetable")
    List<Words> getWords();
}

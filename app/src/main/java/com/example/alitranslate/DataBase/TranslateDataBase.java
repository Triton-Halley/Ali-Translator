package com.example.alitranslate.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.alitranslate.Model.Words;

@Database(entities = {Words.class},version = 1,exportSchema = false)
@TypeConverters({Words.UUIDConverter.class})
public abstract class TranslateDataBase extends RoomDatabase {
    public abstract TranslateDatabaseDAO TranslateDAO();
}

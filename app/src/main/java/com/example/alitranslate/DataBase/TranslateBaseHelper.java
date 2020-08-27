package com.example.alitranslate.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.alitranslate.DataBase.WordTranslateSchema.TranslateTable.TABLE_NAME_TRANSLATE;

public class TranslateBaseHelper extends SQLiteOpenHelper {

    public TranslateBaseHelper(@Nullable Context context) {
        super(context, WordTranslateSchema.NAME, null, WordTranslateSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_TRANSLATE + "(" +
                WordTranslateSchema.TranslateTable.COLSTranslate.ID + " integer primary key autoincrement," +
                WordTranslateSchema.TranslateTable.COLSTranslate.UUID + " text," +
                WordTranslateSchema.TranslateTable.COLSTranslate.WORD_EN + " text," +
                WordTranslateSchema.TranslateTable.COLSTranslate.WORD_FN + " text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

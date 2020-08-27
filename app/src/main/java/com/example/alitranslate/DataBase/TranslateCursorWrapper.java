package com.example.alitranslate.DataBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.alitranslate.Model.Words;

import java.util.UUID;

public class TranslateCursorWrapper extends CursorWrapper {

    public TranslateCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Words getWords(){
        String stringWordFN=getString(getColumnIndex(WordTranslateSchema.TranslateTable.COLSTranslate.WORD_FN));
        String stringWordEN=getString(getColumnIndex(WordTranslateSchema.TranslateTable.COLSTranslate.WORD_EN));
        String stringUUID=getString(getColumnIndex(WordTranslateSchema.TranslateTable.COLSTranslate.UUID));
        Words words = new Words(UUID.fromString(stringUUID));
        words.setWordEn(stringWordEN);
        words.setWordFa(stringWordFN);
        return words ;
    }
}

package com.example.alitranslate.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alitranslate.DataBase.TranslateBaseHelper;
import com.example.alitranslate.DataBase.TranslateCursorWrapper;
import com.example.alitranslate.DataBase.WordTranslateSchema;
import com.example.alitranslate.Model.Words;

import java.util.ArrayList;
import java.util.List;

public class TranslateRepository implements IRepository  {
    private static TranslateRepository sTranslateRepository ;
    private static Context mContext ;

    private SQLiteDatabase mDatabase ;
    public static TranslateRepository getInstance(Context context){
        mContext = context.getApplicationContext();
        if (sTranslateRepository==null){
            sTranslateRepository =new TranslateRepository();
        }
        return sTranslateRepository ;
    }
    private TranslateRepository(){
        TranslateBaseHelper helper = new TranslateBaseHelper(mContext);
        mDatabase = helper.getWritableDatabase();
    }
    private TranslateCursorWrapper queryWords(String selection,String[] selectionArgs){
        Cursor cursor = mDatabase.query(WordTranslateSchema.TranslateTable.TABLE_NAME_TRANSLATE,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        TranslateCursorWrapper wrapper = new TranslateCursorWrapper(cursor);
        return wrapper;
    }
    private ContentValues getWordsContentValue(Words words){
        ContentValues values = new ContentValues();
        values.put(WordTranslateSchema.TranslateTable.COLSTranslate.UUID,words.getWordsID().toString());
        values.put(WordTranslateSchema.TranslateTable.COLSTranslate.WORD_EN,words.getWordEn());
        values.put(WordTranslateSchema.TranslateTable.COLSTranslate.WORD_FN,words.getWordFa());
        return values ;
    }

    @Override
    public List<Words> getWordsList() {
        List<Words> mWords = new ArrayList<>();
        TranslateCursorWrapper cursor = queryWords(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                mWords.add(cursor.getWords());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return mWords;
    }

    @Override
    public void insetWords(Words words) {
        ContentValues values = getWordsContentValue(words);
        mDatabase.insert(WordTranslateSchema.TranslateTable.TABLE_NAME_TRANSLATE,
                null,values);
    }

    @Override
    public void updateWords(Words words) {
        ContentValues values = getWordsContentValue(words);
        String where = WordTranslateSchema.TranslateTable.COLSTranslate.WORD_EN+ "=? and =?" +
                WordTranslateSchema.TranslateTable.COLSTranslate.WORD_FN ;
        String[] whereArgs = new String[]{words.getWordEn(),words.getWordFa()};
        mDatabase.update(WordTranslateSchema.TranslateTable.TABLE_NAME_TRANSLATE,values,where,whereArgs);
    }

    @Override
    public void deleteWords(Words words) {
        String where = WordTranslateSchema.TranslateTable.COLSTranslate.WORD_EN+ "=?and" +
                WordTranslateSchema.TranslateTable.COLSTranslate.WORD_FN ;
        String[] whereArgs = new String[]{words.getWordEn(),words.getWordFa()};
        mDatabase.delete(WordTranslateSchema.TranslateTable.TABLE_NAME_TRANSLATE,where,whereArgs);
    }
}

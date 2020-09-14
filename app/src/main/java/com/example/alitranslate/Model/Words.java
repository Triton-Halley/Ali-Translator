package com.example.alitranslate.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.UUID;
@Entity(tableName = "TranslateTable")
public class Words implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "uuid")
    private UUID wordsID;
    @ColumnInfo(name = "Persian_Word")
    private String WordFa = "" ;
    @ColumnInfo(name = "English_Words")
    private String WordEn = "" ;
//    private String WordFr = "" ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getWordsID() {
        return wordsID;
    }

    public void setWordsID(UUID wordsID) {
        this.wordsID = wordsID;
    }

    public Words() {
        this(UUID.randomUUID());
    }

    public Words(UUID wordsID) {
        this.wordsID = wordsID;
    }

    public String getWordFa() {
        return WordFa;
    }

    public void setWordFa(String wordFa) {
        WordFa = wordFa;
    }

    public String getWordEn() {
        return WordEn;
    }

    public void setWordEn(String wordEn) {
        WordEn = wordEn;
    }

    public static class UUIDConverter{
        @TypeConverter
        public String UUIDToString(UUID value){
            return value.toString();
        }
        @TypeConverter
        public UUID fromString(String value){
            return UUID.fromString(value);
        }
    }
}

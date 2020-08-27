package com.example.alitranslate.DataBase;

public class WordTranslateSchema {
    public static final String NAME = "translateDB.db";
    public static final int VERSION = 1 ;
    public static final class TranslateTable{
        public static final String TABLE_NAME_TRANSLATE ="TranslateTable";

        public static final class COLSTranslate{
            public static final String ID = "words_id";
            public static final String UUID = "words_uuid";
            public static final String WORD_FN = "word_farsi";
            public static final String WORD_EN = "word_English";

        }
    }
}

package com.example.alitranslate.Model;

import java.util.UUID;

public class Words {
    private UUID wordsID;
    private String WordFa = "" ;
    private String WordEn = "" ;

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
}

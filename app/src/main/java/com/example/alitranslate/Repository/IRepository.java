package com.example.alitranslate.Repository;

import com.example.alitranslate.Model.Words;

import java.util.List;

public interface IRepository {
    List<Words> getWordsList();

    void insetWords(Words words);

    void updateWords(Words words);

    void deleteWords(Words words);
}

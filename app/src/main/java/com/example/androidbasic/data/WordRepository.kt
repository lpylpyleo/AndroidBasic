package com.example.androidbasic.data

import androidx.annotation.WorkerThread
import com.example.androidbasic.data.dao.WordDao
import com.example.androidbasic.data.model.Word

class WordRepository(private val wordDao: WordDao) {
    val allWords = wordDao.getAlphabetizedWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
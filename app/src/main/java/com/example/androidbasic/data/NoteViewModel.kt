package com.example.androidbasic.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbasic.data.WordRepository
import com.example.androidbasic.data.model.Word
import kotlinx.coroutines.launch

class NoteViewModel(private val wordRepository: WordRepository) : ViewModel() {
    val allWords = wordRepository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}

class NoteViewModelFactory(private val wordRepository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
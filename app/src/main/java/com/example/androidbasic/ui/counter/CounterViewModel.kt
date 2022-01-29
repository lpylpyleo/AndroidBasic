package com.example.androidbasic.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val counter = MutableLiveData(0)

    fun doFabTab() {
        counter.value = (counter.value ?: 0) + 1
    }
}
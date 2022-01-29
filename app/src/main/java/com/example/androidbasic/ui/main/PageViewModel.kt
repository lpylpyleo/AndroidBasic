package com.example.androidbasic.ui.main

import androidx.lifecycle.*
import com.example.androidbasic.data.VideoRepository
import com.example.androidbasic.data.model.Video
import kotlinx.coroutines.launch

class PageViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    private val _index = MutableLiveData<Int>()
    val videos = MutableLiveData<List<Video>>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getRecommend() = viewModelScope.launch {
         videos.value = videoRepository.getRecommendVideos("BV1xx411c7m9").body()?.data
    }
}
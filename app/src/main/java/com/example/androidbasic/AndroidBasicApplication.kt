package com.example.androidbasic

import android.app.Application
import com.example.androidbasic.data.VideoRepository
import com.example.androidbasic.data.WordRepository
import com.example.androidbasic.data.database.WordRoomDatabase
import com.example.androidbasic.data.http.videoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AndroidBasicApplication : Application() {
    private val scope = CoroutineScope(SupervisorJob())
    private val database by lazy { WordRoomDatabase.getDatabase(this, scope) }
    val wordRepository by lazy { WordRepository(database.wordDao()) }
    val videoRepository by lazy { VideoRepository(videoService) }
}
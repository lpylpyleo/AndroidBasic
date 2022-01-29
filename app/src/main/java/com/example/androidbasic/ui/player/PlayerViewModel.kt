package com.example.androidbasic.ui.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidbasic.data.VideoRepository
import com.example.androidbasic.data.http.videoService
import com.example.androidbasic.data.model.Video
import com.example.androidbasic.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: VideoRepository,private val video: Video): ViewModel() {
    private var _player: ExoPlayer? = null
    private val player
        get() = _player!!

    fun setupPlayer(context: Context,binding: ActivityPlayerBinding){
        _player = ExoPlayer.Builder(context).build()
        val playerView = binding.playerView
        playerView.player = _player

        viewModelScope.launch {
            val playURL = repository.getStreamUrl(video.bvid,video.cid)
            println(playURL.body())
            playURL.body()?.data?.durl?.forEach { url ->
                player.addMediaItem(MediaItem.fromUri(url.url))
            }
            player.prepare()
            player.play()
        }

    }

    private fun destroyPlayer(){
        player.stop()
        player.release()
        _player = null
    }

    override fun onCleared() {
        super.onCleared()
        destroyPlayer()
    }
}

@Suppress("UNCHECKED_CAST")
class PlayerViewModelProvider(private val video: Video) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerViewModel(VideoRepository(videoService),video) as T
    }
}
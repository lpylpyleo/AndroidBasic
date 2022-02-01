package com.example.androidbasic.ui.player

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidbasic.data.VideoRepository
import com.example.androidbasic.data.http.videoService
import com.example.androidbasic.data.model.Video
import com.example.androidbasic.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: VideoRepository, private val video: Video) :
    ViewModel() {
    private var _player: ExoPlayer? = null
    private val player
        get() = _player!!

    fun setupPlayer(context: Context, binding: ActivityPlayerBinding) {
        _player = ExoPlayer.Builder(context).build()
        val playerView = binding.playerView
        val layout = playerView.layoutParams
        layout.height = (layout.width * video.dimension.height / video.dimension.width).toInt()
        playerView.layoutParams = layout
        playerView.requestLayout()
        playerView.forceLayout()
        playerView.player = _player

        viewModelScope.launch {
            val playURL = repository.getStreamUrl(video.bvid, video.cid)
            val factory = DefaultHttpDataSource.Factory()
            factory.setUserAgent("Mozilla/5.0 BiliDroid/6.58.0 (bbcallen@gmail.com)")
            factory.setDefaultRequestProperties(mapOf("Referer" to "https://www.bilibili.com"))
            val dataSource = ProgressiveMediaSource.Factory(factory)
            playURL.body()?.data?.durl?.forEach { url ->
                player.addMediaSource(dataSource.createMediaSource(MediaItem.fromUri(url.url)))
            }
            player.prepare()
            player.play()
            Log.d(TAG, "setupPlayer: play")
        }

    }

    fun destroyPlayer() {
        player.stop()
        player.release()
        _player = null
    }

    companion object{
        private const val TAG = "PlayerViewModel"
    }
}

@Suppress("UNCHECKED_CAST")
class PlayerViewModelProvider(private val video: Video) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerViewModel(VideoRepository(videoService), video) as T
    }
}
package com.example.androidbasic.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.androidbasic.data.model.Video
import com.example.androidbasic.databinding.ActivityPlayerBinding
import com.example.androidbasic.util.ObjectWrapperBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private val viewModel by viewModels<PlayerViewModel> {
        PlayerViewModelProvider(
            (intent.extras!!.getBinder(
                ARG_VIDEO_BVID
            )!! as ObjectWrapperBinder).obj as  Video
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setupPlayer(baseContext, binding)
    }

    companion object {
        val ARG_VIDEO_BVID = "ARG_VIDEO_BVID"
    }
}
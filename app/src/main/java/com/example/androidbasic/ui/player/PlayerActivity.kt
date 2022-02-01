package com.example.androidbasic.ui.player

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasic.data.model.Video
import com.example.androidbasic.databinding.ActivityPlayerBinding
import com.example.androidbasic.util.ObjectWrapperBinder

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
        Log.d(TAG, "onCreate: ")
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setupPlayer(baseContext, binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroyPlayer()
    }

    companion object {
        const val ARG_VIDEO_BVID = "ARG_VIDEO_BVID"
        private const val TAG = "PlayerActivity"
    }
}
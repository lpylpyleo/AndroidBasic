package com.example.androidbasic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasic.databinding.ActivityMainBinding
import com.example.androidbasic.ui.counter.CounterActivity
import com.example.androidbasic.ui.login.LoginActivity
import com.example.androidbasic.ui.note.NoteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.counterButton.setOnClickListener {
            startActivity(Intent(this, CounterActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.noteButton.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
        }
        binding.tabButton.setOnClickListener {
            startActivity(Intent(this, BilibiliActivity::class.java))
        }
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}
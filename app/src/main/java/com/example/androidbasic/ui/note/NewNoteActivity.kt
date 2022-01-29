package com.example.androidbasic.ui.note

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.androidbasic.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent()
            val text = binding.newWord.text.toString()
            if (TextUtils.isEmpty(text)) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra("result", text)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
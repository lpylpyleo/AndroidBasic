package com.example.androidbasic.ui.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.androidbasic.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CounterActivity : AppCompatActivity() {
    private val vm : CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        val textView = findViewById<TextView>(R.id.count)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            vm.doFabTab()
        }

        textView.text = vm.counter.value.toString()

        vm.counter.observe(this,  {
            textView.text = it.toString()
        })
    }
}
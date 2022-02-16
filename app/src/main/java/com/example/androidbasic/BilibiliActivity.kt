package com.example.androidbasic

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.androidbasic.databinding.ActivityBilibiliBinding
import com.example.androidbasic.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.lang.reflect.Field


class BilibiliActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBilibiliBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBilibiliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        // set initial tab to index 1.
        tabs.setScrollPosition(1,.0f,false)
        viewPager.currentItem = 1

        val fab: FloatingActionButton = binding.fab
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action") { println("tap") }.show()
        }
        getTypeFaces()
    }

    override fun onUserInteraction() {
        if (currentFocus != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    @SuppressLint("DiscouragedPrivateApi")
    private fun getTypeFaces(){
        val typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        val f: Field = Typeface::class.java.getDeclaredField("sSystemFontMap")
        f.isAccessible = true
        val sSystemFontMap = f.get(typeface) as Map<*, *>
        for ((key, value) in sSystemFontMap.entries) {
            Log.d("FontMap", "$key ---> $value\n")
        }
    }
}
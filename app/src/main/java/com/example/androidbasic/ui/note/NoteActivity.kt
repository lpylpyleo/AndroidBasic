package com.example.androidbasic.ui.note

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbasic.AndroidBasicApplication
import com.example.androidbasic.R
import com.example.androidbasic.data.model.Word
import com.example.androidbasic.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as AndroidBasicApplication).wordRepository)
    }
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val res = result.data?.getStringExtra("result")
                viewModel.insert(Word(res!!))
            } else {
                Toast.makeText(this, "No input", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adaptor = NoteListAdaptor()
        binding.notesRecyclerView.adapter = adaptor
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this@NoteActivity)

        binding.addNote.setOnClickListener {
            resultLauncher.launch(Intent(this, NewNoteActivity::class.java))
        }

        viewModel.allWords.observe(this) { words ->
            words.let {
                println(it)
                adaptor.submitList(it)
            }
        }
    }

}


class NoteListAdaptor : ListAdapter<Word, NoteListAdaptor.WordViewHolder>(WordsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position).word)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.note)
        fun bind(text: String?) {
            textView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_view_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
    }
}
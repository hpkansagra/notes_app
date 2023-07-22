package com.example.notes_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes_app.Adapter.NotesAdapter
import com.example.notes_app.Database.NoteDatabase
import com.example.notes_app.Models.Note
import com.example.notes_app.Models.NoteViewModel
import com.example.notes_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDatabase
    lateinit var  viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter
    lateinit var selectNote: Note


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing the ui

        iniUI()
                viewModel = ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application))get(NoteViewModel::class.java)

                viewModel.allnotes.observel(this){ list->

                    list.let{

                    adapter.updateList(list)


                    }

                }
                database = NoteDatabase.getDatabase(this)

    }

    private fun iniUI() {

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(LinearLayout.VERTICAL)
        adapter = NotesAdapter(this,this)
        binding.recyclerView.adapter = adapter

        val getCount = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ ->
            if (result.resultcode == Activity.RESULT_OK){

                val result
                val note = result.data?.getSerilizableExtra("note")as? Note
                if (note !=null){

                    viewModel.insertNote(note)

                }

            }

        }
        binding.fbAddNote.setOnClickListener{
            val insert = Intent(this,addnote::class.java)
            getCount.launch()

        }

    }
}
package com.example.notes_app.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.example.notes_app.Database.NoteDatabase
import com.example.notes_app.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application): AndroidViewModel(application){

    private val repository : NotesRepository

    val allnotes: LiveData<List<Note>>

    init{

        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }

    fun delete1Note(note: Note)=viewModelScope.launch (Dispatchers){

        repository.delete(note)
    }

    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {

        repository.insert(note)
    }

    fun UpdateNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

}
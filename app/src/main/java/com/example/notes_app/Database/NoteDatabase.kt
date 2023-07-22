package com.example.notes_app.Database

import android.content.Context
import  androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_app.Models.Note
import com.example.notes_app.utilites.DATABASE_NAME


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? =null

        fun getDatabase(Context : Context) : NoteDatabase{

            return INSTANCE ?: synchronized(this){


                val instance = Room.databaseBuilder(
                   Context.applicationContext,
                   NoteDatabase::class.java,
                   DATABASE_NAME
               ).build()

                INSTANCE = instance

                instance
            }
        }

    }


}
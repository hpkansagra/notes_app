package com.example.notes_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app.Models.Note
import com.example.notes_app.R

class NotesAdapter(private  val  context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter, position: Int) {
        val currntNote =NoteList[position]
        holder.title.text = currntNote.title
        holder.title.isSelected = true

        holder.Note_tv.text = currntNote.note
        holder.date.text = currntNote.date
        holder.date.isSelected = true

        holder.notes_layout.setBackgroundColor(holder.itemView.resources.getColor(randomColour(),null))

        holder.notes_layout.setOnClickListener{

            listener.onItemClicked(NoteList[holder.adapterposition])

        }

        holder.notes_layout.setOnLongClickListener{

            holder.OnLongClicked(NoteList[holder.adapterposition],holder.notes_layout)
            true

        }

    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newlist :List<Note>){

        fullList.clear()
        fullList.addAll(newlist)

        NoteList.clear()
        NoteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search : String){

        NoteList.clear()

        for (item in fullList){

            if (item.title?.lowercase()?.contains(search.lowercase()) ==true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){

                NoteList.add(item)
            }
        }


    }



    fun randomColour() : Int{


        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex
    }

    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val note_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val tital = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    interface NotesClickListener{
        fun onitemClicked(note: Note)
        fun onlongClicked(note: Note,cardView: CardView)
    }


}


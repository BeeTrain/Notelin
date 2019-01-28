package chernakov.ru.notelin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import chernakov.ru.notelin.R
import chernakov.ru.notelin.mvp.models.Note
import chernakov.ru.notelin.utils.formatDate

class NotesAdapter(notesList: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

	private var mNotesList: List<Note> = notesList

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
		var v = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)

		return ViewHolder(v)
	}

	override fun onBindViewHolder(holder:ViewHolder, position: Int) {
		var note = mNotesList[position];
		holder.mNoteTitle.text = note.title;

		holder.mNoteDate.text = formatDate(note.changeDate);
	}

	override fun getItemCount(): Int {
		return mNotesList.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		var mNoteTitle: TextView

		var mNoteDate: TextView

		init {
			mNoteTitle = itemView.findViewById(R.id.tvNoteTitle) as TextView
			mNoteDate = itemView.findViewById(R.id.tvNoteDate) as TextView
		}

	}
}
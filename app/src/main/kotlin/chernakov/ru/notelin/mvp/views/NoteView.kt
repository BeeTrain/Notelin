package chernakov.ru.notelin.mvp.views

import chernakov.ru.notelin.mvp.models.Note
import com.arellomobile.mvp.MvpView

interface NoteView : MvpView {
	
	fun showNote(note: Note)
	
	fun onNoteSaved()
	
	fun onNoteDeleted()
	
	fun showNoteInfoDialog(noteInfo: String)
	
	fun hideNoteInfoDialog()
	
	fun showNoteDeleteDialog()
	
	fun hideNoteDeleteDialog()
	
}
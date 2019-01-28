package chernakov.ru.notelin.mvp.presenters

import chernakov.ru.notelin.NotelinApp
import chernakov.ru.notelin.mvp.models.Note
import chernakov.ru.notelin.mvp.models.NoteDao
import chernakov.ru.notelin.mvp.views.NoteView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.util.*
import javax.inject.Inject

@InjectViewState
class NotePresenter(val noteId: Long) : MvpPresenter<NoteView>() {
	
	@Inject
	lateinit var mNoteDao: NoteDao
	lateinit var mNote: Note
	
	init {
		NotelinApp.graph.inject(this)
	}
	
	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		
		mNote = mNoteDao.getNoteById(noteId)!!
		viewState.showNote(mNote)
	}
	
	fun showNote(noteId: Long) {
		mNote = mNoteDao.getNoteById(noteId)
		viewState.showNote(mNote)
	}
	
	fun saveNote(title: String, text: String) {
		mNote.title = title
		mNote.text = text
		mNote.changeDate = Date()
		mNoteDao.saveNote(mNote)
//		EventBus.getDefault().post(NoteEditAction(mNote.id))
		viewState.onNoteSaved()
	}
	
	fun showNoteDeleteDialog() {
		viewState.showNoteDeleteDialog()
	}
	
	fun hideNoteDeleteDialog() {
		viewState.hideNoteDeleteDialog()
	}
	
	fun showNoteInfoDialog() {
		viewState.showNoteInfoDialog(mNote.getInfo())
	}
	
	fun hideNoteInfoDialog() {
		viewState.hideNoteInfoDialog()
	}
}
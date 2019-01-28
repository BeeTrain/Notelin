package chernakov.ru.notelin.mvp.presenters

import android.app.Activity
import android.content.Intent
import chernakov.ru.notelin.NotelinApp
import chernakov.ru.notelin.mvp.models.Note
import chernakov.ru.notelin.mvp.models.NoteDao
import chernakov.ru.notelin.mvp.views.MainView
import chernakov.ru.notelin.ui.activities.NoteActivity
import chernakov.ru.notelin.utils.getNotesSortMethodName
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
	
	enum class SortNotesBy : Comparator<Note> {
		DATE {
			override fun compare(note1: Note, note2: Note) = note1.changeDate!!.compareTo(note2.changeDate)
		},
		NAME {
			override fun compare(note1: Note, note2: Note) = note1.title!!.compareTo(note2.title!!)
		},
	}
	
	@Inject
	lateinit var mNoteDao: NoteDao
	private lateinit var mNotesList: MutableList<Note>

	init {
		NotelinApp.graph.inject(this)
	}

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		loadAllNotes()
	}

	fun loadAllNotes() {
		mNotesList = mNoteDao.loadAllNotes()
		viewState.onNotesLoaded(mNotesList)
	}
	
	/**
	 * Открывает активити с заметкой по позиции
	 */
	fun openNote(activity: Activity, position: Int) {
		val intent = Intent(activity, NoteActivity::class.java)
		intent.putExtra("note_id", mNotesList[position].id)
		activity.startActivity(intent)
	}
	
	fun openNewNote(activity: Activity) {
		val newNote = mNoteDao.createNote()
		mNotesList.add(newNote)
		sortNotesBy(getCurrentSortMethod())
		openNote(activity, mNotesList.indexOf(newNote))
	}
	
	/**
	 * Ищет заметку по имени
	 */
	fun search(query: String) {
		if (query.equals("")) {
			viewState.onSearchResult(mNotesList)
		} else {
			val searchResults = mNotesList.filter { it.title!!.startsWith(query, ignoreCase = true) }
			viewState.onSearchResult(searchResults)
		}
	}
	
	/**
	 * Сортирует заметки
	 */
	fun sortNotesBy(sortMethod: SortNotesBy) {
		mNotesList.sortWith(sortMethod)
		viewState.updateView()
	}
	
	fun showNoteContextDialog(position: Int) {
		viewState.showNoteContextDialog(position)
	}
	
	private fun getCurrentSortMethod(): SortNotesBy {
		val defaultSortMethodName = SortNotesBy.DATE.toString()
		val currentSortMethodName = getNotesSortMethodName(defaultSortMethodName)
		return SortNotesBy.valueOf(currentSortMethodName)
	}
	
	private fun getNotePositionById(noteId: Long) = mNotesList.indexOfFirst { it.id == noteId }
	
}
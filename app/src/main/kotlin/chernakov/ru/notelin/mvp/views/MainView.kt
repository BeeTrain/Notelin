package chernakov.ru.notelin.mvp.views

import chernakov.ru.notelin.mvp.models.Note
import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

	fun onNotesLoaded(notes: List<Note>)

	fun updateView()

	fun onSearchResult(notes: List<Note>)

	fun onAllNotesDeleted()

	fun showNoteInfoDialog(noteInfo: String)

	fun hideNoteInfoDialog()

	fun showNoteDeleteDialog(notePosition: Int)

	fun hideNoteDeleteDialog()

	fun showNoteContextDialog(notePosition: Int)

	fun hideNoteContextDialog()
}
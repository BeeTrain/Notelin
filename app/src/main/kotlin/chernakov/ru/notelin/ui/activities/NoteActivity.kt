package chernakov.ru.notelin.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import chernakov.ru.notelin.R
import chernakov.ru.notelin.mvp.models.Note
import chernakov.ru.notelin.mvp.presenters.NotePresenter
import chernakov.ru.notelin.mvp.views.NoteView
import chernakov.ru.notelin.utils.formatDate
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : MvpAppCompatActivity(), NoteView {
	override fun showNote(note: Note) {
		tvNoteDate.text = formatDate(note.changeDate)
		etTitle.setText(note.title)
		etText.setText(note.text)
	}
	
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_note, menu)
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menuSaveNote -> mPresenter.saveNote(etTitle.text.toString(), etText.text.toString())
		}
		return super.onOptionsItemSelected(item)
	}
	
	override fun onNoteSaved() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun onNoteDeleted() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun showNoteInfoDialog(noteInfo: String) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun hideNoteInfoDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun showNoteDeleteDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun hideNoteDeleteDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	@InjectPresenter
	lateinit var mPresenter: NotePresenter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_note)
		
		val noteId = intent.extras.getLong("note_id", -1)
		mPresenter.showNote(noteId)
	}
	
}
package chernakov.ru.notelin.ui.activities

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import chernakov.ru.notelin.R
import chernakov.ru.notelin.R.layout.activity_main
import chernakov.ru.notelin.mvp.models.Note
import chernakov.ru.notelin.mvp.presenters.MainPresenter
import chernakov.ru.notelin.mvp.views.MainView
import chernakov.ru.notelin.ui.adapters.NotesAdapter
import chernakov.ru.notelin.ui.commons.ItemClickSupport
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.pawegio.kandroid.onQueryChange
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity(), MainView {
	
	@InjectPresenter
	lateinit var mPresenter: MainPresenter
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		
		with(ItemClickSupport.addTo(rvNotesList)) {
			setOnItemClickListener { recyclerView, position, v ->
				mPresenter.openNote(this@MainActivity, position)
			}
			setOnItemLongClickListener { recyclerView, position, v ->
				mPresenter.showNoteContextDialog(position); true
			}
		}
		
		fabButtonAdd.setOnClickListener {
			mPresenter.openNewNote(this)
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		
		initSearchView(menu)
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menuSortByName -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.NAME)
			R.id.menuSortByDate -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.DATE)
		}
		return super.onOptionsItemSelected(item)
	}
	
	private fun initSearchView(menu: Menu) {
		var searchViewMenuItem = menu.findItem(R.id.action_search);
		var searchView = searchViewMenuItem.actionView as SearchView;
		searchView.onQueryChange { query -> mPresenter.search(query) }
		searchView.setOnCloseListener { mPresenter.search(""); false }
	}
	
	override fun onNotesLoaded(notes: List<Note>) {
		rvNotesList.adapter = NotesAdapter(notes)
		updateView()
	}
	
	override fun updateView() {
		rvNotesList.adapter.notifyDataSetChanged()
		if (rvNotesList.adapter.itemCount == 0) {
			rvNotesList.visibility = View.GONE
			tvNotesIsEmpty.visibility = View.VISIBLE
		} else {
			rvNotesList.visibility = View.VISIBLE
			tvNotesIsEmpty.visibility = View.GONE
		}
	}
	
	override fun onSearchResult(notes: List<Note>) {
		rvNotesList.adapter = NotesAdapter(notes)
	}
	
	override fun onAllNotesDeleted() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun showNoteInfoDialog(noteInfo: String) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun hideNoteInfoDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun showNoteDeleteDialog(notePosition: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun hideNoteDeleteDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun showNoteContextDialog(notePosition: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun hideNoteContextDialog() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}
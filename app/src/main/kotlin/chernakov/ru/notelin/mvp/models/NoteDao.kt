package chernakov.ru.notelin.mvp.models

import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import java.util.*

/**
 * Менеджер работы с заметками.
 *
 * @author Chernakov M.E.
 */
class NoteDao {

	/**
	 * Создает заметку
	 */
	fun createNote(): Note {
		var note = Note("Новая заметка", Date())
		note.save()
		return note
	}

	/**
	 * Сохраняет заметку
	 *
	 * @param note заметка, которую надо сохранить
	 */
	fun saveNote(note: Note) = note.save()

	/**
	 * Загружает все заметки
	 */
	fun loadAllNotes() = Select().from(Note::class.java).execute<Note>()

	/**
	 * Возвращает заметку по идентификатору
	 *
	 * @param noteId идентификатор заметки
	 */
	fun getNoteById(noteId: Long) = Select().from(Note::class.java).where("id = ?", noteId).executeSingle<Note>()

	/**
	 * Удаляет все заметки
	 */
	fun deleteAllNotes() = Delete().from(Note::class.java).execute<Note>()

	/**
	 * Удаляет заметку
	 *
	 * @param note заметка, которую надо удалить
	 */
	fun deleteNote(note: Note) = note.delete()
}
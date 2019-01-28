@file:JvmName("PrefsUtils")

package chernakov.ru.notelin.utils

import android.content.Context



import chernakov.ru.notelin.NotelinApp

private val prefs by lazy {
	NotelinApp.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
}

fun getNotesSortMethodName(defaultValue: String): String = prefs.getString("sort_method", defaultValue)

fun setNotesSortMethod(sortMethod: String) {
	prefs.edit().putString("sort_method", sortMethod).apply()
}
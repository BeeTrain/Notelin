package chernakov.ru.notelin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import chernakov.ru.notelin.di.AppComponent
import chernakov.ru.notelin.di.DaggerAppComponent
import chernakov.ru.notelin.di.NoteDaoModule

class NotelinApp: Application() {

	companion object {
		lateinit var graph: AppComponent
		
		@SuppressLint("StaticFieldLeak")
		lateinit var context: Context
	}

	override fun onCreate() {
		super.onCreate()

		graph = DaggerAppComponent.builder().noteDaoModule(NoteDaoModule()).build()

	}
}
package chernakov.ru.notelin.di

import chernakov.ru.notelin.mvp.presenters.MainPresenter
import chernakov.ru.notelin.mvp.presenters.NotePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NoteDaoModule::class))
interface AppComponent {
	
	fun inject(mainPresenter: MainPresenter)
	fun inject(notePresenter: NotePresenter)
}

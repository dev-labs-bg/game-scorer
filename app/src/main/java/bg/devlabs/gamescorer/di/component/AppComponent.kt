package bg.devlabs.gamescorer.di.component

import bg.devlabs.gamescorer.GameScorerApp
import bg.devlabs.gamescorer.MainActivity
import bg.devlabs.gamescorer.di.module.AppModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 09.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(application: GameScorerApp)

    fun inject(activity: MainActivity)
}
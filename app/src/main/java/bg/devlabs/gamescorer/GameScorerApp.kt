package bg.devlabs.gamescorer

import android.app.Application
import bg.devlabs.gamescorer.di.component.AppComponent
import bg.devlabs.gamescorer.di.component.DaggerAppComponent


/**
 * Created by Slavi Petrov on 09.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class GameScorerApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent.builder().build()
    }
}
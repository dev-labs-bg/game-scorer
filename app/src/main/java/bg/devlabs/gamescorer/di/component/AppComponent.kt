package bg.devlabs.gamescorer.di.component

import bg.devlabs.gamescorer.di.module.AppModule
import bg.devlabs.gamescorer.di.module.Bindings
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 09.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
@Component(modules = arrayOf(Bindings::class, AppModule::class))
interface AppComponent {

    fun activityComponent(): ActivityComponent
}
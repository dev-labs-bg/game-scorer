package bg.devlabs.gamescorer.di

import android.app.Application
import bg.devlabs.gamescorer.GameScorerApp
import bg.devlabs.gamescorer.di.module.ActivityBindingModule
import bg.devlabs.gamescorer.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 09.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GameScorerApp)
}
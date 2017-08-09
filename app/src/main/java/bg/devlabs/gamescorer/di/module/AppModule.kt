package bg.devlabs.gamescorer.di.module

import android.app.Application
import bg.devlabs.gamescorer.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


/**
 * Created by Slavi Petrov on 09.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Module
class AppModule(val app: Application) {

    @Provides
    @ApplicationScope
    fun provideApp() = app

}
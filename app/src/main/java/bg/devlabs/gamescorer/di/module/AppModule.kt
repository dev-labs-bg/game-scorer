package bg.devlabs.gamescorer.di.module

import bg.devlabs.gamescorer.data.realtime_database.RealtimeDatabase
import bg.devlabs.gamescorer.data.realtime_database.RealtimeDatabaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 *
 * Class which is used to provide all of the necessary classes for dependency injection.
 */
@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideRealtimeDatabase(realtimeDatabase: RealtimeDatabaseImpl) : RealtimeDatabase {
        return realtimeDatabase
    }
}
package bg.devlabs.gamescorer.di.module

import bg.devlabs.gamescorer.data.DataManager
import bg.devlabs.gamescorer.data.DataManagerImpl
import dagger.Binds
import dagger.Module


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Module
abstract class Bindings {

    @Binds
    internal abstract fun bindDataManager(dataManager: DataManagerImpl): DataManager
}
package bg.devlabs.gamescorer.di.module

import bg.devlabs.gamescorer.di.ActivityScope
import bg.devlabs.gamescorer.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Slavi Petrov on 26.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [LoginModule::class])
    @ActivityScope
    abstract fun loginActivity(): LoginActivity
}
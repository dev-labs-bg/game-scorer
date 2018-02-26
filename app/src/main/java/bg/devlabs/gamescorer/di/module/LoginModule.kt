package bg.devlabs.gamescorer.di.module

import bg.devlabs.gamescorer.ui.login.LoginActivity
import bg.devlabs.gamescorer.ui.login.LoginContract
import bg.devlabs.gamescorer.ui.login.LoginPresenter
import dagger.Binds
import dagger.Module


/**
 * Created by Slavi Petrov on 26.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
@Module
abstract class LoginModule {
    @Binds
    abstract fun provideLoginView(view: LoginActivity): LoginContract.View

    @Binds
    abstract fun provideLoginPresenter(presenter: LoginPresenter): LoginContract.Presenter
}
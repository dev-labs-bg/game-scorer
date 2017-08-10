package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.MvpView


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface LoginView : MvpView {

    fun onLoginSuccess()

    fun onLoginFailure()
}
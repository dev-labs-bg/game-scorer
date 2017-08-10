package bg.devlabs.gamescorer.di.component

import bg.devlabs.gamescorer.di.scope.ActivityScope
import bg.devlabs.gamescorer.ui.login.LoginActivity
import bg.devlabs.gamescorer.ui.main.MainActivity
import dagger.Subcomponent


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)
}
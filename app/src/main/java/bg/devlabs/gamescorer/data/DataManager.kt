package bg.devlabs.gamescorer.data

import bg.devlabs.gamescorer.ui.login.OnUserSignedInListener


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface DataManager {
    fun signInEmail(email: String, password: String, listener: OnUserSignedInListener)
    fun signInGoogle()
}
package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.MvpView
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface LoginView : MvpView {
    fun onLoginSuccess()
    fun onLoginFailure()
    fun fieldsValid(): Boolean
    fun getDefaultWebClientId(): String?
    fun buildGoogleApiClient(gso: GoogleSignInOptions): GoogleApiClient
    fun startGoogleLoginActivity(googleApiClient: GoogleApiClient)
}
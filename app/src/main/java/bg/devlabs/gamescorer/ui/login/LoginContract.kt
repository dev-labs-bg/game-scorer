package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.BaseContract
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient


/**
 * Created by Slavi Petrov on 26.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
interface LoginContract {

    interface View : BaseContract.View {
        fun onLoginSuccess()
        fun onLoginFailure()
        fun fieldsValid(): Boolean
        fun getDefaultWebClientId(): String?
        fun buildGoogleApiClient(gso: GoogleSignInOptions): GoogleApiClient
        fun startGoogleLoginActivity(googleApiClient: GoogleApiClient)
    }

    interface Presenter {

    }
}
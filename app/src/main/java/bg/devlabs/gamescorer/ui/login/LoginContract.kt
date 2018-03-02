package bg.devlabs.gamescorer.ui.login

import android.content.Intent
import bg.devlabs.gamescorer.ui.base.BaseContract
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession


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
        fun startGoogleLoginActivity(googleSignInClient: GoogleSignInClient)
    }

    interface Presenter : BaseContract.Presenter {
        fun onLoginButtonClicked(email: String, password: String)
        fun onGoogleButtonClicked()
        fun onFacebookButtonClicked()
        fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun handleTwitterSignIn(): Callback<TwitterSession>?
    }
}
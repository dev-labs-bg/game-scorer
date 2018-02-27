package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class LoginPresenter @Inject constructor(view: LoginContract.View)
    : BasePresenter<LoginContract.View>(view), LoginContract.Presenter {

    fun onLoginButtonClicked(email: String, password: String) {
        if (view!!.fieldsValid()) {
            signInFirebase(email, password)
        }
    }

    private fun signInFirebase(email: String, password: String) {
        compositeDisposable.add(dataManager.signInEmail(email, password)
                .subscribe({

                }, {
                    view!!.showInfoDialog(it.localizedMessage)
                }))
    }

    fun onGoogleButtonClicked() {
        view!!.startGoogleLoginActivity(dataManager.googleSignInClient)

        dataManager.signInGoogle()
    }

    fun onFacebookButtonClicked() {

    }

    fun onTwitterButtonClicked() {

    }

    fun onSignUpButtonClicked() {

    }

    fun handleSignInResult(result: GoogleSignInResult?) {
        if (result!!.isSuccess) {
            // The user is logged in
            var account = result.signInAccount
            // TODO: Start new activity
        } else {
            // The user has completed or dismissed the Google Sign In
        }
    }
}

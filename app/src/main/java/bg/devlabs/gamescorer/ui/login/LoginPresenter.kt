package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class LoginPresenter @Inject constructor(view: LoginContract.View)
    : BasePresenter<LoginContract.View>(view), LoginContract.Presenter {

    override fun onLoginButtonClicked(email: String, password: String) {
        view?.let {
            if (it.fieldsValid()) {
                signInFirebase(email, password)
            }
        }
    }

    private fun signInFirebase(email: String, password: String) {
        compositeDisposable.add(dataManager.signInEmail(email, password)
                .subscribe({
                    // TODO: Open app's Main screen
                }, {
                    view?.showInfoDialog(it.localizedMessage)
                }))
    }

    override fun onGoogleButtonClicked() {
        view?.startGoogleLoginActivity(dataManager.googleSignInClient)
    }

    override fun onFacebookButtonClicked() {
        val dbReference = FirebaseDatabase.getInstance().getReference("notifications")
                .child("invitations").push()
        dbReference.setValue(Notification("Title", "Author"))
    }

    data class Notification(val title: String, val author: String)

    override fun onTwitterButtonClicked() {

    }

    override fun onSignUpButtonClicked() {

    }

    override fun handleSignInResult(result: GoogleSignInResult?) {
        result?.let {
            if (result.isSuccess) {
                compositeDisposable.add(dataManager.signInGoogle(result.signInAccount)
                        .subscribe({
                            // TODO: Open app's Main screen
                        }, {
                            view?.showInfoDialog(it.localizedMessage)
                        }))
                // The user is logged in
                dataManager.writeUserInfo(result.signInAccount)
            } else {
                // There is an error or the user cancelled the login process
            }
        }
    }
}

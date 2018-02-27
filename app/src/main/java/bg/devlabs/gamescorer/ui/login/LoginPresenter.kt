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

    override fun onGoogleButtonClicked() {
        view!!.startGoogleLoginActivity(dataManager.googleSignInClient)

        dataManager.signInGoogle()
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
        if (result!!.isSuccess) {
            // The user is logged in
            var account = result.signInAccount
            // TODO: Start new activity
        } else {
            // The user has completed or dismissed the Google Sign In
        }
    }
}

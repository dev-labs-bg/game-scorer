package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.data.db.model.Invitation
import bg.devlabs.gamescorer.ui.base.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
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
        val currentUser = FirebaseAuth.getInstance().currentUser
        FirebaseDatabase.getInstance().reference
                .child("users")
                .child(currentUser?.uid)
                .child("invitations")
                // TODO: Consider changing the invitation id instead of passing a different one every time
                .push()
                .setValue(Invitation("Invitation text", currentUser?.uid))
    }

    override fun onTwitterButtonClicked() {

    }

    override fun onSignUpButtonClicked() {

    }

    override fun handleSignInResult(result: GoogleSignInResult?) {
        result?.let {
            if (result.isSuccess) {
                // The user is logged in
                val signInAccount = result.signInAccount
                compositeDisposable.add(
                        dataManager.signInGoogle(signInAccount).subscribe({
                            if (it.isSuccessful) {
                                val displayName = signInAccount?.displayName
                                val email = signInAccount?.email
                                val photoUrl = signInAccount?.photoUrl
                                // TODO: Open app's Main screen
                                // Write the user to the database
                                dataManager.writeUserInfo(displayName,
                                        email,
                                        photoUrl.toString())
                            } else {
                                // Authentication error
                            }
                        }, {

                        })
                )
            } else {
                // There is an error or the user cancelled the login process
            }
        }
    }
}

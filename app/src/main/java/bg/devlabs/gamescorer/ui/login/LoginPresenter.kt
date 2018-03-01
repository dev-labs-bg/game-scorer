package bg.devlabs.gamescorer.ui.login

import android.content.Intent
import bg.devlabs.gamescorer.ui.base.BasePresenter
import bg.devlabs.gamescorer.ui.main.MainActivity
import bg.devlabs.gamescorer.utils.Constants.RC_SIGN_IN
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
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
        compositeDisposable.add(dataManager.initFacebookSignIn()
                .flatMap {
                    dataManager.signInFacebook(it.accessToken)
                }
                .subscribe({
                    // TODO: Start Main Activity

                    view?.startActivity(MainActivity::class.java)
                }, {
                    view?.showToast(it.localizedMessage)
                })
        )
    }

    override fun onTwitterButtonClicked() {

    }

    override fun onSignUpButtonClicked() {

    }

    override fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleGoogleSignIn(result)
        } else { // Facebook login
            dataManager.handleFacebookSignIn(requestCode, resultCode, data)
        }
    }

    private fun handleGoogleSignIn(result: GoogleSignInResult?) {
        result?.let {
            if (result.isSuccess) {
                // The user is logged in
                val signInAccount = result.signInAccount
                compositeDisposable.add(
                        dataManager.signInGoogle(signInAccount).subscribe({
                            val displayName = signInAccount?.displayName
                            val email = signInAccount?.email
                            val photoUrl = signInAccount?.photoUrl
                            // TODO: Open app's Main screen
                            // Write the user to the database
                            dataManager.writeUserInfo(displayName,
                                    email,
                                    photoUrl.toString())
                        }, {

                        })
                )
            } else {
                // There is an error or the user cancelled the login process
            }
        }
    }
}

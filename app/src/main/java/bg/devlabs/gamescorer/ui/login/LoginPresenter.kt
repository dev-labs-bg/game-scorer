package bg.devlabs.gamescorer.ui.login

import android.content.Intent
import bg.devlabs.gamescorer.data.db.model.AuthType
import bg.devlabs.gamescorer.ui.base.BasePresenter
import bg.devlabs.gamescorer.ui.main.MainActivity
import bg.devlabs.gamescorer.utils.Constants.RC_SIGN_IN_GOOGLE
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
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
                .flatMap { loginResult ->
                    dataManager.signInFacebook(loginResult.accessToken)
                }
                .map {
                    val user = it.result.user
                    dataManager.writeUserInfo(user.displayName,
                            user.email,
                            user.photoUrl.toString(),
                            AuthType.FACEBOOK)
                }
                .subscribe({
                    view?.startActivity(MainActivity::class.java)
                }, {
                    view?.showToast(it.localizedMessage)
                })
        )
    }

    override fun onTwitterButtonClicked() {
//        dataManager.initTwitterSignIn()
    }

    override fun onSignUpButtonClicked() {

    }

    override fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_GOOGLE) {
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
                                    photoUrl.toString(),
                                    AuthType.GOOGLE)
                        }, {
                            view?.showToast(it.localizedMessage)
                        })
                )
            } else {
                // There is an error or the user cancelled the login process
            }
        }
    }

    override fun handleTwitterSignIn(): Callback<TwitterSession>? {
        return object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                compositeDisposable.add(dataManager.signInTwitter(result?.data)
                        .subscribe({
                            val user = it.result.user
                            val displayName = user.displayName
                            val email = user.email
                            val photoUrl = user.photoUrl
                            dataManager.writeUserInfo(displayName,
                                    email,
                                    photoUrl.toString(),
                                    AuthType.TWITTER)
                        }, {
                            view?.showToast(it.localizedMessage)
                        })
                )
            }

            override fun failure(exception: TwitterException?) {
                exception?.let {
                    view?.showToast(it.localizedMessage)
                }
            }
        }
    }
}

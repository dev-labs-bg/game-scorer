package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.ui.base.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Single
import io.reactivex.functions.BiFunction
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
                .setValue(Notification("Title", "Author"))
    }

    data class Notification(val title: String, val author: String)

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
                        Single.zip(dataManager.signInGoogle(signInAccount),
                                dataManager.getCurrentUserTokenId(),
                                BiFunction { task: Task<AuthResult>, tokenResult: String? ->
                                    val displayName = signInAccount?.displayName
                                    val email = signInAccount?.email
                                    val photoUrl = signInAccount?.photoUrl
                                    val tokenMap = HashMap<String, String?>()
                                    tokenMap["token_id"] = tokenResult
                                    dataManager.writeUserInfo(displayName, email, photoUrl.toString(),
                                            tokenMap)
                                })
                                .subscribe()
                )
//                compositeDisposable.add(dataManager.signInGoogle(result.signInAccount)
//                        .subscribe({
//                            // TODO: Open app's Main screen
//                            // Write the user to the database
//                            dataManager.writeUserInfo(result.signInAccount)
//                        }, {
//                            view?.showInfoDialog(it.localizedMessage)
//                        }))
            } else {
                // There is an error or the user cancelled the login process
            }
        }
    }
}

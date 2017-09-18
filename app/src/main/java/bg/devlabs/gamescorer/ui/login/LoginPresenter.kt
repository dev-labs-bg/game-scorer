package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.data.DataManager
import bg.devlabs.gamescorer.ui.base.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import javax.inject.Inject




/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class LoginPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<LoginView>() {
    lateinit var googleApiClient: GoogleApiClient

    fun onLoginButtonClicked(email: String, password: String) {
        if (view!!.fieldsValid()) {
            signInFirebase(email, password)
        }
    }

    private fun signInFirebase(email: String, password: String) {
        dataManager.signInEmail(email, password, object: OnUserSignedInListener{
            override fun onSuccess(dataSnapshot: DataSnapshot, firebaseUser: FirebaseUser) {
                // TODO: Start new activity
            }

            override fun onFailure(task: Task<AuthResult>) {
                view!!.showInfoDialog(task.exception!!.message!!)
            }
        })
    }

    fun onGoogleButtonClicked() {
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(view!!.getDefaultWebClientId())
                .requestEmail()
                .build()

        googleApiClient = view!!.buildGoogleApiClient(gso)

        view!!.startGoogleLoginActivity(googleApiClient)

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

package bg.devlabs.gamescorer.ui.login

import bg.devlabs.gamescorer.data.DataManager
import bg.devlabs.gamescorer.ui.base.BasePresenter
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
class LoginPresenter @Inject constructor(val dataManager: DataManager) : BasePresenter<LoginView>() {
    fun onLoginButtonClicked(email: String, password: String) {
        dataManager.signInEmail(email, password, object: OnUserSignedInListener{
            override fun onSuccess(dataSnapshot: DataSnapshot, firebaseUser: FirebaseUser) {
                
            }

            override fun onFailure(task: Task<AuthResult>) {

            }
        })
    }

    fun onGoogleButtonClicked() {

    }

    fun onFacebookButtonClicked() {

    }

    fun onTwitterButtonClicked() {

    }

    fun onSignUpButtonClicked() {

    }
}

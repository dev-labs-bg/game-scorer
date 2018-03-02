package bg.devlabs.gamescorer.data.auth

import android.content.Intent
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.twitter.sdk.android.core.TwitterSession
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
class AppAuthHelper @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                        private val facebookCallbackManager: CallbackManager,
                                        override val googleSignInClient: GoogleSignInClient)
    : AuthHelper {

    override fun signInEmail(email: String, password: String): Single<Task<AuthResult>> {
        return Single.create {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
//                        listener.onSuccess()
                            it.onSuccess(task)
                        } else {
                            it.onError(Throwable(task.exception?.localizedMessage))
                        }
                    }
        }
    }

    override fun signInGoogle(signInAccount: GoogleSignInAccount?): Single<Task<AuthResult>> {
        return Single.create { single ->
            val credential = GoogleAuthProvider.getCredential(signInAccount?.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            single.onSuccess(it)
                        } else {
                            single.onError(Throwable(it.exception?.localizedMessage))
                        }
                    }
        }
    }

    override fun initFacebookSignIn(): Single<LoginResult> {
        return Single.create {
            LoginManager.getInstance().registerCallback(facebookCallbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult) {
                            it.onSuccess(result)
                        }

                        override fun onCancel() {

                        }

                        override fun onError(error: FacebookException) {
                            it.onError(error)
                        }
                    })
        }
    }

    override fun handleFacebookSignIn(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun signInFacebook(accessToken: AccessToken): Single<Task<AuthResult>> {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        return Single.create { single ->
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            single.onSuccess(it)
                        } else {
                            single.onError(Throwable(it.exception?.localizedMessage))
                        }
                    }
        }
    }

    override fun signInTwitter(session: TwitterSession?): Single<Task<AuthResult>> {
        return Single.create { single ->
            session?.authToken?.let {
                val credential = TwitterAuthProvider.getCredential(it.token, it.secret)
                firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                single.onSuccess(it)
                            } else {
                                single.onError(Throwable(it.exception?.localizedMessage))
                            }
                        }
            }
        }
    }
}
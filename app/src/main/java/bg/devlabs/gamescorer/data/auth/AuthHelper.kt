package bg.devlabs.gamescorer.data.auth

import android.content.Intent
import com.facebook.AccessToken
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.twitter.sdk.android.core.TwitterSession
import io.reactivex.Single


/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
interface AuthHelper {
    val googleSignInClient: GoogleSignInClient

    fun signInEmail(email: String, password: String): Single<Task<AuthResult>>

    fun signInGoogle(signInAccount: GoogleSignInAccount?): Single<Task<AuthResult>>

    fun initFacebookSignIn(): Single<LoginResult>

    fun signInFacebook(accessToken: AccessToken): Single<Task<AuthResult>>

    fun handleFacebookSignIn(requestCode: Int, resultCode: Int, data: Intent?)

    fun signInTwitter(session: TwitterSession?): Single<Task<AuthResult>>
}
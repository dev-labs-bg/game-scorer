package bg.devlabs.gamescorer.data

import android.content.Intent
import bg.devlabs.gamescorer.data.auth.AuthHelper
import bg.devlabs.gamescorer.data.db.RealtimeDbHelper
import bg.devlabs.gamescorer.data.db.model.AuthType
import bg.devlabs.gamescorer.data.db.model.Invitation
import bg.devlabs.gamescorer.utils.prepare
import com.facebook.AccessToken
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.twitter.sdk.android.core.TwitterSession
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
class AppDataManager @Inject
constructor(private val realtimeDbHelper: RealtimeDbHelper,
            private val authHelper: AuthHelper) : DataManager {
    override val googleSignInClient: GoogleSignInClient
        get() = authHelper.googleSignInClient

    override fun signInEmail(email: String, password: String): Single<Task<AuthResult>> {
        return authHelper.signInEmail(email, password).prepare()
    }

    override fun signInGoogle(signInAccount: GoogleSignInAccount?): Single<Task<AuthResult>> {
        return authHelper.signInGoogle(signInAccount).prepare()
    }

    override fun initFacebookSignIn(): Single<LoginResult> {
        return authHelper.initFacebookSignIn().prepare()
    }

    override fun signInFacebook(accessToken: AccessToken): Single<Task<AuthResult>> {
        return authHelper.signInFacebook(accessToken).prepare()
    }

    override fun handleFacebookSignIn(requestCode: Int, resultCode: Int, data: Intent?) {
        authHelper.handleFacebookSignIn(requestCode, resultCode, data)
    }

    override fun signInTwitter(session: TwitterSession?): Single<Task<AuthResult>> {
        return authHelper.signInTwitter(session).prepare()
    }

    override fun writeUserInfo(displayName: String?,
                               email: String?,
                               photoUrl: String?,
                               authType: AuthType?) {

        realtimeDbHelper.writeUserInfo(displayName, email, photoUrl, authType)
    }

    override fun getCurrentUserTokenId(): Single<String?> {
        return realtimeDbHelper.getCurrentUserTokenId()
    }

    // TODO: Add this when needed to send game invitation
    fun sendInvitation() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        FirebaseDatabase.getInstance().reference
                .child("users")
                .child(currentUser?.uid)
                .child("invitations")
                // TODO: Consider changing the invitation id instead of passing a different one every time
                .push()
                .setValue(Invitation("Invitation text", currentUser?.uid))
    }
}
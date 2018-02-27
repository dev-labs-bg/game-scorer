package bg.devlabs.gamescorer.data

import bg.devlabs.gamescorer.data.auth.AuthHelper
import bg.devlabs.gamescorer.data.db.RealtimeDbHelper
import bg.devlabs.gamescorer.utils.extensions.prepare
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
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

    override fun signInEmail(email: String, password: String): Observable<Task<AuthResult>> {
        return authHelper.signInEmail(email, password).prepare()
    }

    override fun signInGoogle() {

    }

    override fun writeUserInfo(signInAccount: GoogleSignInAccount?) {
        realtimeDbHelper.writeUserInfo(signInAccount)
    }
}
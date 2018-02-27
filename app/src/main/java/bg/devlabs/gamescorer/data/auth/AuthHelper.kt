package bg.devlabs.gamescorer.data.auth

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable


/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
interface AuthHelper {
    val googleSignInClient: GoogleSignInClient

    fun signInEmail(email: String, password: String): Observable<Task<AuthResult>>

    fun signInGoogle()
}
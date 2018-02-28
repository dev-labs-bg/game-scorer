package bg.devlabs.gamescorer.data.auth

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
class AppAuthHelper @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                        override val googleSignInClient: GoogleSignInClient)
    : AuthHelper {

    override fun signInEmail(email: String, password: String): Single<Task<AuthResult>> {
        return Single.create {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase Login", "Successful login!")
//                        listener.onSuccess()
                            it.onSuccess(task)
                        } else {
                            Log.d("Firebase Login", "Login failed!")
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
                            single.onError(Throwable("Failed to sign in with Google!"))
                        }
                    }
        }
    }
}
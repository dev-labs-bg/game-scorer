package bg.devlabs.gamescorer.data.auth

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
class AppAuthHelper @Inject constructor(val context: Context,
                                        val firebaseAuth: FirebaseAuth,
                                        override val googleSignInClient: GoogleSignInClient)
    : AuthHelper {

    override fun signInEmail(email: String, password: String): Observable<Task<AuthResult>> {
        return Observable.create<Task<AuthResult>> {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase Login", "Successful login!")
//                        listener.onSuccess()
                            it.onNext(task)
                        } else {
                            Log.d("Firebase Login", "Login failed!")
                            it.onError(Throwable(task.exception?.localizedMessage))
                        }
                    }
        }
    }

    override fun signInGoogle() {

    }
}
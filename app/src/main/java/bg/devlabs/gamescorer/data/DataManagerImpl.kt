package bg.devlabs.gamescorer.data

import android.util.Log
import bg.devlabs.gamescorer.data.realtime_database.RealtimeDatabase
import bg.devlabs.gamescorer.ui.login.OnUserSignedInListener
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
internal class DataManagerImpl @Inject constructor(val realtimeDatabase: RealtimeDatabase) : DataManager {
    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun signInEmail(email: String, password: String, listener: OnUserSignedInListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase Login", "Successful login!")
//                        listener.onSuccess()
                    } else {
                        Log.d("Firebase Login", "Login failed!!")
                        listener.onFailure(task)
                    }
                }
    }

    override fun signInGoogle() {

    }
}
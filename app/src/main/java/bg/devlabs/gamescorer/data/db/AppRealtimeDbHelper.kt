package bg.devlabs.gamescorer.data.db

import bg.devlabs.gamescorer.data.db.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
class AppRealtimeDbHelper @Inject constructor(private val database: DatabaseReference)
    : RealtimeDbHelper {

    override fun writeUserInfo(signInAccount: GoogleSignInAccount?) {
        val user = User(signInAccount?.displayName,
                signInAccount?.email,
                signInAccount?.photoUrl.toString())
        database.child("users").child(signInAccount?.id).setValue(user)
    }
}
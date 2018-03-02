package bg.devlabs.gamescorer.data.db

import bg.devlabs.gamescorer.data.db.model.AuthType
import bg.devlabs.gamescorer.data.db.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
@Singleton
class AppRealtimeDbHelper @Inject constructor(private val database: DatabaseReference,
                                              private val firebaseAuth: FirebaseAuth)
    : RealtimeDbHelper {

    override fun writeUserInfo(displayName: String?,
                               email: String?,
                               photoUrl: String?,
                               authType: AuthType?) {
        // TODO: Remove this token on user logout
        val tokenId = FirebaseInstanceId.getInstance().token
        val user = User(displayName, email, photoUrl, tokenId, authType)
        database.child("users").child(firebaseAuth.currentUser?.uid).setValue(user)
    }

    override fun getCurrentUserTokenId(): Single<String?> {
        return Single.create { single ->
            firebaseAuth.currentUser?.let {
                it.getIdToken(true).addOnSuccessListener {
                    it.token?.let { token -> single.onSuccess(token) }
                }
            }
        }
    }
}
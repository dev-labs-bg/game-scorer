package bg.devlabs.gamescorer.data.db

import com.google.android.gms.auth.api.signin.GoogleSignInAccount


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface RealtimeDbHelper {
    fun writeUserInfo(signInAccount: GoogleSignInAccount?)
}
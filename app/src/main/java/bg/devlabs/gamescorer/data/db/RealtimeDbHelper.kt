package bg.devlabs.gamescorer.data.db

import bg.devlabs.gamescorer.data.db.model.AuthType
import io.reactivex.Single


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface RealtimeDbHelper {
    fun writeUserInfo(displayName: String?, email: String?, photoUrl: String?, authType: AuthType?)

    fun getCurrentUserTokenId(): Single<String?>
}
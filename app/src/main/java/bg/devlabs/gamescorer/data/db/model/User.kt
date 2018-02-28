package bg.devlabs.gamescorer.data.db.model


@Suppress("unused")
/**
 * Created by Slavi Petrov on 27.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
class User {

    var displayName: String? = null
    var email: String? = null
    var photoUrl: String? = null
    var tokenMap: HashMap<String, String?>? = null

    constructor()

    constructor(displayName: String?, email: String?, photoUrl: String?,
                tokenMap: HashMap<String, String?>) {
        this.displayName = displayName
        this.email = email
        this.photoUrl = photoUrl
        this.tokenMap = tokenMap
    }
}
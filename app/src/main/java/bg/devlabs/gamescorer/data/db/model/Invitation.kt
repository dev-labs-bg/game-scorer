package bg.devlabs.gamescorer.data.db.model


@Suppress("unused")
/**
 * Created by Slavi Petrov on 01.03.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
class Invitation {
    var message: String? = null
    var fromUserId: String? = null

    constructor()

    constructor(message: String?, fromUserId: String?) {
        this.message = message
        this.fromUserId = fromUserId
    }
}
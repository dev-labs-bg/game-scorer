package bg.devlabs.gamescorer.data.db.model


/**
 * Created by Slavi Petrov on 02.03.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
enum class AuthType(val authType: Int) {
    EMAIL(0), GOOGLE(1), FACEBOOK(2), TWITTER(3)
}
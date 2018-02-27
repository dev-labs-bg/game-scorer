package bg.devlabs.gamescorer.data

import bg.devlabs.gamescorer.data.auth.AuthHelper
import bg.devlabs.gamescorer.data.db.RealtimeDbHelper


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface DataManager : RealtimeDbHelper, AuthHelper {

}
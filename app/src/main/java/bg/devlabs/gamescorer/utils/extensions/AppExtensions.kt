package bg.devlabs.gamescorer.utils.extensions

import android.content.Context
import bg.devlabs.gamescorer.GameScorerApp
import bg.devlabs.gamescorer.di.component.AppComponent


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */

fun Context.getAppComponent(): AppComponent = (applicationContext as GameScorerApp).appComponent
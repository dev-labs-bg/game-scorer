package bg.devlabs.gamescorer.ui.base

import android.os.Bundle
import dagger.android.AndroidInjection


/**
 * Created by Slavi Petrov on 26.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class InjectionBaseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}
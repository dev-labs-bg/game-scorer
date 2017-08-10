package bg.devlabs.gamescorer.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bg.devlabs.gamescorer.di.component.ActivityComponent
import bg.devlabs.gamescorer.utils.extensions.getAppComponent


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        activityComponent = getAppComponent().activityComponent()
    }

    override fun onDestroy() {
        activityComponent = null
        super.onDestroy()
    }

    protected abstract fun getLayoutResId(): Int
}
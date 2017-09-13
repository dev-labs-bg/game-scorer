package bg.devlabs.gamescorer.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.di.component.ActivityComponent
import bg.devlabs.gamescorer.utils.extensions.getAppComponent
import bg.devlabs.gamescorer.utils.extensions.show

/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {

    protected var activityComponent: ActivityComponent? = null
    private lateinit var progressDialog: Dialog

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

    override fun showProgress(messageResId: Int) {
        progressDialog = Dialog(this)
        progressDialog.show(getString(messageResId), true, false)
    }

    override fun dismissProgress() {
        progressDialog.dismiss()
    }

    override fun showInfoDialog(message: String) {
        AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(message)
                .setPositiveButton(R.string.dismiss) { _, _ -> }
                .show()
    }
}
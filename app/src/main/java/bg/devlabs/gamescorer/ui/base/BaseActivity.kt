package bg.devlabs.gamescorer.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.di.component.ActivityComponent
import bg.devlabs.gamescorer.utils.show

/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    protected var activityComponent: ActivityComponent? = null
    private lateinit var progressDialog: Dialog
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
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

    override fun showInfoDialog(messageResId: Int) {
        AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(getString(messageResId))
                .setPositiveButton(R.string.dismiss) { _, _ -> }
                .show()
    }

    override fun showToast(messageResId: Int) {
        showToast(getString(messageResId))
    }

    override fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun startActivity(targetActivity: Class<*>) {
        startActivity(Intent(this, targetActivity))
    }
}
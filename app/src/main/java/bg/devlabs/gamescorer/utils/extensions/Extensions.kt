package bg.devlabs.gamescorer.utils.extensions

import android.app.Dialog
import bg.devlabs.gamescorer.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog.*


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
fun Dialog.show(message: String, indeterminate: Boolean, cancelable: Boolean) {
    this.setContentView(R.layout.dialog)
    this.progressBar.isIndeterminate = indeterminate
    this.title.text = message
    this.setCancelable(cancelable)
    this.show()
}

fun <T> Single<T>.prepare() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
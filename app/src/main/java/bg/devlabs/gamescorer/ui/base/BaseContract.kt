package bg.devlabs.gamescorer.ui.base


/**
 * Created by Slavi Petrov on 26.02.2018
 * Dev Labs
 * slavi@devlabs.bg
 */
interface BaseContract {

    interface View {
        fun showProgress(messageResId: Int)
        fun dismissProgress()
        fun showInfoDialog(message: String)
        fun showInfoDialog(messageResId: Int)
    }

    interface Presenter {

    }
}
package bg.devlabs.gamescorer.ui.base


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class BasePresenter<T: MvpView> {
    protected var view: T? = null

    fun onAttach(view: T) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }
}
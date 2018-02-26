package bg.devlabs.gamescorer.ui.base

import bg.devlabs.gamescorer.data.DataManager
import javax.inject.Inject


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
abstract class BasePresenter<V : BaseContract.View>(var view: V?) : BaseContract.Presenter {

    @Inject
    lateinit var dataManager: DataManager

    fun onDetach() {
        this.view = null
    }
}
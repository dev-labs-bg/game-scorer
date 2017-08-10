package bg.devlabs.gamescorer.ui.login

import android.os.Bundle
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.ui.base.BaseActivity
import javax.inject.Inject

open class LoginActivity : BaseActivity(), LoginView {

    @Inject lateinit var presenter: LoginPresenter

    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)

        presenter.bind(this)
    }

    override fun onLoginSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

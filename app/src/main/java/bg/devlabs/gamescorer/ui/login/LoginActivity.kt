package bg.devlabs.gamescorer.ui.login

import android.os.Bundle
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

open class LoginActivity : BaseActivity(), LoginView {

    @Inject lateinit var presenter: LoginPresenter

    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)

        presenter.onAttach(this)

        initButtonListeners()
    }

    private fun initButtonListeners() {
        loginButton.setOnClickListener {
            presenter.onLoginButtonClicked()
        }

        googleButton.setOnClickListener {
            presenter.onGoogleButtonClicked()
        }

        facebookButton.setOnClickListener {
            presenter.onFacebookButtonClicked()
        }

        twitterButton.setOnClickListener {
            presenter.onTwitterButtonClicked()
        }

        signUpButton.setOnClickListener {
            presenter.onSignUpButtonClicked()
        }
    }

    override fun onLoginSuccess() {

    }

    override fun onLoginFailure() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}

package bg.devlabs.gamescorer.ui.login

import android.content.Intent
import android.os.Bundle
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.ui.base.InjectionBaseActivity
import bg.devlabs.gamescorer.utils.Constants.FACEBOOK_READ_PERMISSIONS
import bg.devlabs.gamescorer.utils.Constants.RC_SIGN_IN
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : InjectionBaseActivity(), LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)
        initButtonListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.handleOnActivityResult(requestCode, resultCode, data)
    }

    private fun initButtonListeners() {
        login_button.setOnClickListener {
            presenter.onLoginButtonClicked(username_edit_text.text.toString(),
                    password_edit_text.text.toString())
        }

        google_button.setOnClickListener {
            presenter.onGoogleButtonClicked()
        }

        facebook_button.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, FACEBOOK_READ_PERMISSIONS)
            presenter.onFacebookButtonClicked()
        }

        twitter_button.setOnClickListener {
            presenter.onTwitterButtonClicked()
        }

        sign_up_button.setOnClickListener {
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

    override fun fieldsValid(): Boolean {
        if (username_edit_text.text.isEmpty()) {
            showToast(R.string.username_empty_warning)
            return false
        }

        if (password_edit_text.text.isEmpty()) {
            showToast(R.string.password_empty_warning)
            return false
        }

        return true
    }

    override fun getDefaultWebClientId(): String? = getString(R.string.default_web_client_id)

    override fun buildGoogleApiClient(gso: GoogleSignInOptions): GoogleApiClient {
        return GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        showInfoDialog(p0.errorMessage.toString())
    }

    override fun startGoogleLoginActivity(googleSignInClient: GoogleSignInClient) {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }
}

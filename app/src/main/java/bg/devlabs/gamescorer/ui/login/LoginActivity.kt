package bg.devlabs.gamescorer.ui.login

import android.content.Intent
import android.os.Bundle
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.ui.base.BaseActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject



open class LoginActivity : BaseActivity(), LoginView, GoogleApiClient.OnConnectionFailedListener {
    private val RC_SIGN_IN = 9001

    @Inject lateinit var presenter: LoginPresenter

    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)

        presenter.onAttach(this)

        initButtonListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            presenter.handleSignInResult(result)
        }
    }

    private fun initButtonListeners() {
        loginButton.setOnClickListener {
            presenter.onLoginButtonClicked(usernameView.text, passwordView.text)
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

    override fun fieldsValid(): Boolean {
        if (usernameView.text.isEmpty()) {
            usernameView.setError(getString(R.string.username_empty_warning))
            return false
        }

        if (passwordView.text.isEmpty()) {
            passwordView.setError(getString(R.string.password_empty_warning))
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

    override fun startGoogleLoginActivity(googleApiClient: GoogleApiClient) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
}

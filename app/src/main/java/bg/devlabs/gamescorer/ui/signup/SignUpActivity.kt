package bg.devlabs.gamescorer.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bg.devlabs.gamescorer.R

class SignUpActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, SignUpActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
}

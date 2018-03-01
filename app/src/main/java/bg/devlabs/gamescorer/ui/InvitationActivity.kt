package bg.devlabs.gamescorer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bg.devlabs.gamescorer.R
import kotlinx.android.synthetic.main.activity_invitation.*

class InvitationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation)

        val dataMessage = intent.getStringExtra("dataMessage")
        val dataFromId = intent.getStringExtra("dataFromId")

        invitation_data_text_view.text = "FROM : $dataFromId | MESSAGE : $dataMessage"
    }
}

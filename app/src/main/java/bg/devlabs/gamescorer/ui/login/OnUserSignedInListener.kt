package bg.devlabs.gamescorer.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot




/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
interface OnUserSignedInListener {
    fun onSuccess(dataSnapshot: DataSnapshot, firebaseUser: FirebaseUser)
    fun onFailure(task: Task<AuthResult>)
}
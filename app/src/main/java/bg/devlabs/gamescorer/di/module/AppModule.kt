package bg.devlabs.gamescorer.di.module

import android.app.Application
import android.content.Context
import bg.devlabs.gamescorer.R
import bg.devlabs.gamescorer.data.AppDataManager
import bg.devlabs.gamescorer.data.DataManager
import bg.devlabs.gamescorer.data.auth.AppAuthHelper
import bg.devlabs.gamescorer.data.auth.AuthHelper
import bg.devlabs.gamescorer.data.db.AppRealtimeDbHelper
import bg.devlabs.gamescorer.data.db.RealtimeDbHelper
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 *
 * Class which is used to provide all of the necessary classes for dependency injection.
 */
@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @Singleton
    fun provideRealtimeDbHelper(appRealtimeDbHelper: AppRealtimeDbHelper): RealtimeDbHelper =
            appRealtimeDbHelper

    @Provides
    @Singleton
    fun provideAuthHelper(appAuthHelper: AppAuthHelper): AuthHelper = appAuthHelper

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideGoogleApiClient(context: Context): GoogleSignInClient {
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        return GoogleSignIn.getClient(context, gso)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @Provides
    @Singleton
    fun provideFacebookCallbackManager(): CallbackManager = CallbackManager.Factory.create()
}
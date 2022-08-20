package `in`.live.tamasha

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TamashaApp : Application() {

    companion object {
        private var appContext: Context? = null
        fun getAppContext(): Context? {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}
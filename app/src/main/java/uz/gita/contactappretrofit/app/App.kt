package uz.gita.contactappretrofit.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.contactappretrofit.data.local.MySharedPref

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
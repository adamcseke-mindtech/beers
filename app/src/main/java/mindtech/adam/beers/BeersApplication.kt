package mindtech.adam.beers

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeersApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
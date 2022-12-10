package com.android.hindara.booking.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        var IS_RTL: Boolean = false
    }
}

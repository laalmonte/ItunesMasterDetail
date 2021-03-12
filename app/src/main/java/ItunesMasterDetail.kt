package com.viyahe.itunesmasterdetail

import android.app.Application
import com.viyahe.itunesmasterdetail.util.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ItunesMasterDetail : Application() {

    override fun onCreate() {
        super.onCreate()

        SessionManager.init(this)
    }
}
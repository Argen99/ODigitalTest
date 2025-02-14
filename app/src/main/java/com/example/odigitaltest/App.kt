package com.example.odigitaltest

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.odigitaltest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }
}
package com.promecarus.gucca

import android.app.Application
import com.promecarus.core.di.CoreModule.databaseModule
import com.promecarus.core.di.CoreModule.networkModule
import com.promecarus.core.di.CoreModule.preferenceModule
import com.promecarus.core.di.CoreModule.repositoryModule
import com.promecarus.core.di.CoreModule.useCaseModule
import com.promecarus.core.di.CoreModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@MainApplication)
            modules(
                modules = listOf(
                    databaseModule,
                    preferenceModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                ),
            )
        }
    }
}

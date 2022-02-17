/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.palushkin.kotlintestapp.common.di.app.AppComponent
import com.palushkin.kotlintestapp.common.di.app.AppModule
import com.palushkin.kotlintestapp.common.di.app.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : MultiDexApplication() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private var dataRefreshed = false

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun delayedInit() {
        applicationScope.launch {
            doGooglePlayServices()
        }
    }

    private fun doGooglePlayServices() {
        try {
            ProviderInstaller.installIfNeeded(applicationContext)
        } catch (e: GooglePlayServicesRepairableException) {
            GoogleApiAvailability.getInstance()
                    .showErrorNotification(applicationContext, e.connectionStatusCode)
            return
        } catch (e: GooglePlayServicesNotAvailableException) {
            //syncResult.stats.numAuthExceptions++
            return
        }
    }

    fun succeedDataRefresh() {
        dataRefreshed = true
    }

    fun checkDataRefresh() = dataRefreshed

    override fun onCreate() {

        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        delayedInit()
    }
}
package id.depok.depoksinglewindow

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.support.multidex.MultiDex
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.crashlytics.android.Crashlytics
import com.orhanobut.hawk.Hawk
import id.depok.depoksinglewindow.di.appModules
import io.fabric.sdk.android.Fabric
import org.koin.Koin
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger



/**
 * Created by PiNGUiN on 2017-12-08.
 */

@GlideModule
class MyAppGlideModule : AppGlideModule()

class DepokSingleViewApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        // Display some logs
        val isDebug = (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
        if (isDebug) {
            Koin.logger = AndroidLogger()
        }

        if (!BuildConfig.DEBUG) {
            // init Crashlytics for release build
            Fabric.with(this, Crashlytics())
        }

        // Init Hawk
        Hawk.init(this).build()

        // Start Koin
        startKoin(this, appModules())
    }
}
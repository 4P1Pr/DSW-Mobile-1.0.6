package id.depok.depoksinglewindow.manager

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by PiNGUiN on 2017-12-10.
 */
class NetManager(private var applicationContext: Context) {

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}
package infinuma.android.shows.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkConnection(private val appContext: Context) {

    fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
package com.example.recruitmentapp.ui.main

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recruitmentapp.utils.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkConnection: NetworkConnection) :
    ViewModel() {

    private val _isNetworkConnected = MutableLiveData(networkConnection.isConnected())
    val isNetworkConnected: LiveData<Boolean> = _isNetworkConnected

    init {
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val connectivityManager = networkConnection.connectivityManager
        connectivityManager.registerNetworkCallback(
            networkConnection.networkRequest,
            getNetworkCallback()
        )
    }

    private fun getNetworkCallback(): ConnectivityManager.NetworkCallback {
        var numConnected = 0
        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                numConnected++
                _isNetworkConnected.postValue(true)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                _isNetworkConnected.postValue(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                numConnected = (numConnected - 1).coerceAtLeast(0)
                if (numConnected == 0) {
                    _isNetworkConnected.postValue(false)
                }
            }
        }
    }
}
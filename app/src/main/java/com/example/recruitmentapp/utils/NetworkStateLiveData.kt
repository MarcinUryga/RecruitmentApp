package com.example.recruitmentapp.utils

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateLiveData @Inject constructor(
    private val networkConnection: NetworkConnection
) : LiveData<Boolean>() {



}

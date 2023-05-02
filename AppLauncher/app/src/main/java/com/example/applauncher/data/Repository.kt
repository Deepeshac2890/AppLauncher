package com.example.applauncher.data

import com.example.applauncher.data.network.ApiService

/**
 * Repository is the single source of truth which will provide data to our view model from network call.
 */
class Repository (private val apiService: ApiService) {
    fun getBlockedAppInfo() = apiService.fetchBlockedApiList()
}
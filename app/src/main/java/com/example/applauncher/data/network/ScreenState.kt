package com.example.applauncher.data.network

/**
 * Screen states which have 3 states Error, Loading and Success.
 * We created this so we can handle the states on UI.
 */
sealed class ScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ScreenState<T>(data)

    class Loading<T>(data: T? = null) : ScreenState<T>(data)

    class Error<T>(message: String, data: T? = null) : ScreenState<T>(data, message)
}

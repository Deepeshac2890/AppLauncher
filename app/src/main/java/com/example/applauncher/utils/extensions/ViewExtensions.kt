package com.example.applauncher.utils.extensions

import android.view.View

/**
 * To show or hide the view.
 */
fun View.show(show: Boolean = true) {
    if (show) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
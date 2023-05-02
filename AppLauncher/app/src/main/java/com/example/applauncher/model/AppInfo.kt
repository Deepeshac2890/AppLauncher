package com.example.applauncher.model

import android.graphics.drawable.Drawable

/**
 * Details related to each app entry in the app drawer.
 */
data class AppInfo(
    var label: CharSequence? = null,
    var packageName: CharSequence? = null,
    var icon: Drawable? = null,
    var isEnabled: Boolean = true
)

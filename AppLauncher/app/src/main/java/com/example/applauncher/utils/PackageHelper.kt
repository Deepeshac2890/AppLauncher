package com.example.applauncher.utils

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import com.example.applauncher.model.AppInfo

/**
 * This is a package helper which will give the list of apps that we have. Made this way so it is more testable.
 */
class PackageHelper {
    /**
     * It gets the application list with an option to sort them in an order.
     */
    fun getAppList(application: Application, isSortedOrderNeeded: Boolean): MutableList<AppInfo> {
        val appInfoList = mutableListOf<AppInfo>()
        val pManager: PackageManager = application.packageManager
        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)
        val allApps = pManager.queryIntentActivities(i, 0)
        for (ri in allApps) {
            // Hide our own app from here.
            if (ri.activityInfo.packageName != application.packageName) {
                val app = AppInfo()
                app.label = ri.loadLabel(pManager)
                app.packageName = ri.activityInfo.packageName
                app.icon = ri.activityInfo.loadIcon(pManager)
                app.isEnabled = true
                appInfoList.add(app)
            }
        }
        if (isSortedOrderNeeded) {
            appInfoList.sortWith(compareBy({ it.label.toString() }, { it.label.toString() }))
        }
        return appInfoList
    }
}
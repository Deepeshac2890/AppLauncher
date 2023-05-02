package com.example.applauncher.utils

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PackageHelperTest {

    lateinit var packageHelper: PackageHelper

    @Before
    fun setUp() {
        packageHelper = PackageHelper()
    }

    @Test
    fun getAppList() {
        val appList = packageHelper.getAppList(
            application = ApplicationProvider.getApplicationContext(),
            true
        )
        assertNotEquals(0, appList.size)
        // No need to test the sorting order as this being done via collections which is already tested.
    }
}
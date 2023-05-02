package com.example.applauncher.screens.appDrawer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.applauncher.data.Repository
import com.example.applauncher.data.model.BlockedApiResponse
import com.example.applauncher.data.network.ScreenState
import com.example.applauncher.model.AppInfo
import com.example.applauncher.utils.BLOCKED_API_FAILED_ERROR
import com.example.applauncher.utils.PackageHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Apps Drawer view model which will provide us the data which is needed.
 */
class AppDrawersViewModel(
    private val application: Application,
    private val repository: Repository,
    private val packageHelper: PackageHelper = PackageHelper()
) : AndroidViewModel(application) {
    var appsListData = MutableLiveData<ScreenState<List<AppInfo>>>()
    fun setUpApps() {
        appsListData.value = ScreenState.Loading()

        val appInfoList = packageHelper.getAppList(application, true)

        repository.getBlockedAppInfo().enqueue(object : Callback<BlockedApiResponse> {
            override fun onResponse(
                call: Call<BlockedApiResponse>,
                response: Response<BlockedApiResponse>
            ) {
                if (response.isSuccessful) {
                    val denyList = response.body()?.record?.denyList
                    if (denyList != null) {
                        for (app in appInfoList) {
                            if (app.packageName != null && denyList.contains(app.packageName)) {
                                app.isEnabled = false
                            }
                        }
                    }
                    appsListData.value = ScreenState.Success(appInfoList)
                } else {
                    // Block Api failed. Do not show the list to user. Add screen state here.
                    appsListData.value = ScreenState.Error(BLOCKED_API_FAILED_ERROR)
                }
            }

            override fun onFailure(call: Call<BlockedApiResponse>, t: Throwable) {
                appsListData.value = ScreenState.Error(BLOCKED_API_FAILED_ERROR)
            }
        })
    }
}
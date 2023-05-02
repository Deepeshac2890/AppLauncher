package com.example.applauncher.appDrawer.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applauncher.appDrawer.views.AppListView
import com.example.applauncher.model.AppInfo

/**
 * Adapter class for displaying the apps in drawer.
 */
class AppsDrawerAdapter(private val context: Context, private var appsList: List<AppInfo>) :
    RecyclerView.Adapter<AppsDrawerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AppListView.newInstance(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(appsList[position]) {
            val launchIntent =
                context.packageManager.getLaunchIntentForPackage(appsList[position].packageName.toString())
            context.startActivity(launchIntent)
        }
    }

    override fun getItemCount(): Int {
        return appsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: AppInfo, onClickAction: () -> Unit) {
            if (itemView is AppListView) {
                (itemView as AppListView).bindData(data, onClickAction)
            }
        }
    }
}
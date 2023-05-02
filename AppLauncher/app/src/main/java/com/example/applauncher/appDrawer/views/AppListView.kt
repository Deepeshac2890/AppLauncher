package com.example.applauncher.appDrawer.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.applauncher.R
import com.example.applauncher.databinding.ItemRowViewListBinding
import com.example.applauncher.model.AppInfo

/**
 * The view which contains the app Item which we will show on the drawer.
 */
class AppListView @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null) :
    ConstraintLayout(context, attributes) {
    private var binding: ItemRowViewListBinding = ItemRowViewListBinding.inflate(LayoutInflater.from(context), this)

    fun bindData(data: AppInfo, onClickAction: () -> Unit) {
        if (!data.isEnabled) {
            setBackgroundColor(context.getColor(R.color.grey_ivory))
        } else {
            setBackgroundColor(context.getColor(R.color.white))
        }
        with(binding) {
            this.appIcon.setImageDrawable(data.icon)
            this.appName.text = data.label
        }
        this.setOnClickListener {
            if (data.isEnabled) {
                onClickAction.invoke()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.disabled_app_toast),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        fun newInstance(context: Context) = AppListView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
        }
    }
}
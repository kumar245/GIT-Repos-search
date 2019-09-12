package dev.chaitan.gitusersearch.binding

import android.view.View
import androidx.databinding.BindingAdapter

class ViewBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view: View, isVisible: Boolean) {
            view.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}
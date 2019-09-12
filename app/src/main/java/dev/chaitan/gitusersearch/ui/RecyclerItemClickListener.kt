package dev.chaitan.gitusersearch.ui

import android.view.View

interface RecyclerItemClickListener {
    fun onItemClick(position: Int, view: View)
}
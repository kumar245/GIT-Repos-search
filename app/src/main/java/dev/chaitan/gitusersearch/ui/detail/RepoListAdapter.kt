package dev.chaitan.gitusersearch.ui.detail

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.chaitan.gitusersearch.R
import dev.chaitan.gitusersearch.databinding.ListItemRepoBinding
import dev.chaitan.gitusersearch.model.Repo
import java.util.*

class RepoListAdapter(emptyView: TextView) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private val emptyView= emptyView
    private val repoList = ArrayList<Repo>()
    private val repoListFiltered = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_repo, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.repo = repoListFiltered[position]
        holder.binding.root.tag = position
    }

    fun setItems(list: List<Repo>) {
        repoList.clear()
        repoListFiltered.clear()
        repoList.addAll(list)
        repoListFiltered.addAll(list)
        notifyDataSetChanged()
        updateEmptyView()
    }

    fun filter(keyword: String) {
        repoListFiltered.clear()
        if(TextUtils.isEmpty(keyword)){
            repoListFiltered.addAll(repoList)
        }else{
            repoListFiltered.addAll(
                repoList.filter { it.name.contains(keyword) })
        }

        notifyDataSetChanged()
        updateEmptyView();
    }

    private fun updateEmptyView(){
        emptyView.visibility = if(repoListFiltered.size>0){
            View.GONE
        }else{
            View.VISIBLE
        }
    }
    class ViewHolder(binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding = binding
    }
}
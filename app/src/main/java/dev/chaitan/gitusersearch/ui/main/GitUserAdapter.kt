package dev.chaitan.gitusersearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.chaitan.gitusersearch.R
import dev.chaitan.gitusersearch.databinding.ListItemUserSearchBinding
import dev.chaitan.gitusersearch.model.GitUser
import dev.chaitan.gitusersearch.ui.RecyclerItemClickListener
import java.util.*

class GitUserAdapter(onItemClickListener: RecyclerItemClickListener) :
    RecyclerView.Adapter<GitUserAdapter.ViewHolder>() {

    private val userList = ArrayList<GitUser>()
    private val onItemClickListener: RecyclerItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemUserSearchBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_user_search, parent, false
        )
        return ViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.user = userList[position]
        holder.binding.root.tag = position
    }

    fun addUsers(users: List<GitUser>){
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    class ViewHolder(
        binding: ListItemUserSearchBinding,
        onItemClickListener: RecyclerItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(
                    it.tag as Int,
                    it
                )
            }
        }
    }
}
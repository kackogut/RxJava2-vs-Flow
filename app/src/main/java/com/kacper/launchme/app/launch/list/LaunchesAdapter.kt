package com.kacper.launchme.app.launch.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.databinding.ListItemLaunchBinding

class LaunchesAdapter(private val onItemClick: (Launch) -> (Unit)) :
    PagedListAdapter<Launch, LaunchesAdapter.LaunchViewHolder>
        (Launch.getDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemLaunchBinding.inflate(layoutInflater, parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val awardItem = getItem(position)
        awardItem?.let { holder.bindTo(it, onItemClick) }
    }


    class LaunchViewHolder(
        private val binding: ListItemLaunchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(launch: Launch, onItemClick: (Launch) -> Unit) {
            binding.launch = launch
            binding.root.setOnClickListener { onItemClick(launch) }
            binding.executePendingBindings()
        }
    }
}
package com.example.gitview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.findNavController
import com.example.gitview.databinding.ItemRepositoryBinding

class RepositoriesAdapter(private val onItemClick: (Repository) -> Unit) :
    ListAdapter<Repository, RepositoriesAdapter.RepositoryViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bind(repository)
    }

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.repoName.text = repository.name
            binding.repoDescription.text = repository.description ?: "No description"
            binding.repoLanguage.text = repository.language ?: "N/A"

            // Обновление статистики
            binding.repoStats.text = "⭐ ${repository.stargazers_count}   🍴 ${repository.forks_count}   👀 ${repository.watchers_count}"

            itemView.setOnClickListener {
                onItemClick(repository)
            }
        }
    }

    class RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
    }
}

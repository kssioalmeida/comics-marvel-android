package com.example.comics.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.comics.databinding.ItemListBinding
import com.example.comics.data.ItemVO
import com.example.comics.utils.loadImage

class ComicsListAdapter : ListAdapter<ItemVO, ComicsListAdapter.ComicsItemViewHolder>(
    ComicsItemDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ComicsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ComicsItemViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemVO) {
            with(binding) {
                actionImage.loadImage(item.image)
                actionTitle.text = item.title
                actionSubTitle.text = item.subtitle

            }
        }
    }
}

class ComicsItemDiffUtil : DiffUtil.ItemCallback<ItemVO>() {
    override fun areItemsTheSame(oldItem: ItemVO, newItem: ItemVO): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: ItemVO, newItem: ItemVO): Boolean {
        return oldItem == newItem
    }
}
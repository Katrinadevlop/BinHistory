package com.example.binhistory.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.binhistory.data.BinHistoryEntity
import com.example.binhistory.databinding.ActivitySecondBinding

class BinHistoryAdapter : ListAdapter<BinHistoryEntity, BinHistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BinHistoryEntity>() {
            override fun areItemsTheSame(oldItem: BinHistoryEntity, newItem: BinHistoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BinHistoryEntity, newItem: BinHistoryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivitySecondBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HistoryViewHolder(private val binding: ActivitySecondBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BinHistoryEntity) {
            binding.binText.text = "BIN: ${item.bin}"
            binding.countryText.text = "Страна: ${item.country ?: "Не указано"}"
            binding.bankText.text = "Банк: ${item.bankName ?: "Не указано"}"
        }
    }
}

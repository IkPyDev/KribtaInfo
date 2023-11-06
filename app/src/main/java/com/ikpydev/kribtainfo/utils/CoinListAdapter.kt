package com.ikpydev.kribtainfo.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikpydev.kribtainfo.R
import com.ikpydev.kribtainfo.data.ConstataArgs
import com.ikpydev.kribtainfo.data.dto.CoinModel
import com.ikpydev.kribtainfo.databinding.ItemCoinListBinding

class CoinDiffUtils() : DiffUtil.ItemCallback<CoinModel>() {
    override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
        return oldItem == newItem
    }

}


class CoinListAdapter() :
    ListAdapter<CoinModel, CoinListAdapter.CoinListViewHolder>(CoinDiffUtils()) {
    class CoinListViewHolder(val binding: ItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coinModel: CoinModel) {
            binding.root.setOnClickListener {
                binding.root.findNavController().navigate(
                    R.id.action_coinListFragment_to_coinDetailFragment,
                    bundleOf(ConstataArgs.coinid to coinModel.id)
                )
            }
            binding.CoinNameTv.text = "${coinModel.rank} ${coinModel.name}"
            binding.statusTv.text = if (coinModel.is_active) "Active" else "No Active"
            binding.statusTv.setTextColor(if (coinModel.is_active) Color.GREEN else Color.RED)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            ItemCoinListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
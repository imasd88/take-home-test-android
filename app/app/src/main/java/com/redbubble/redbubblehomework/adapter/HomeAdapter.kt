package com.redbubble.redbubblehomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redbubble.redbubblehomework.BR
import com.redbubble.redbubblehomework.databinding.ItemProductBinding
import com.redbubble.redbubblehomework.model.HomeModel

class HomeAdapter() :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list: List<HomeModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(homeModel: HomeModel) {
            binding.setVariable(BR.viewModel, homeModel)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


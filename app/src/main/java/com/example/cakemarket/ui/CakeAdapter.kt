package com.example.cakemarket.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakemarket.R
import com.example.cakemarket.databinding.ItemCakesBinding
import com.example.cakemarket.model.CakeResponseItem

class CakeAdapter(
    private var cakes: List<CakeResponseItem> = emptyList()  // mutable
) : RecyclerView.Adapter<CakeAdapter.CakeViewHolder>() {

    class CakeViewHolder(val binding: ItemCakesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        val binding = ItemCakesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val cakeItem = cakes[position]

        holder.binding.apply {
            tvCake.text = cakeItem.title
            tvDescription.text = cakeItem.desc

            Glide.with(holder.itemView.context)
                .load("https://services.hanselandpetal.com/photos/${cakeItem.image}")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .circleCrop()
                .into(imgPhoto)
        }
    }

    override fun getItemCount(): Int = cakes.size

    // ✅ Method to update the adapter data
    fun updateCakes(newCakes: List<CakeResponseItem>) {
        cakes = newCakes
        notifyDataSetChanged()
    }
}


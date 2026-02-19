package com.example.cakemarket.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakemarket.R
import com.example.cakemarket.databinding.ItemCakesBinding
import com.example.cakemarket.model.CakeResponseItem

class CakeAdapter(
    private val cakes: List<CakeResponseItem>
) : RecyclerView.Adapter<CakeAdapter.CakeViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CakeViewHolder {
        val binding = ItemCakesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CakeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CakeViewHolder,
        position: Int
    ) {

        holder.binding.apply {
            val cakeItem = cakes[position]
            tvCake.text = cakeItem.title
            tvDescription.text = cakeItem.desc

            Glide.with(holder.itemView.context)
                .load(cakeItem.image)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(imgPhoto)
        }
    }

    override fun getItemCount(): Int {
        return cakes.size

    }
    class CakeViewHolder(val binding: ItemCakesBinding) :
        RecyclerView.ViewHolder(binding.root)
}




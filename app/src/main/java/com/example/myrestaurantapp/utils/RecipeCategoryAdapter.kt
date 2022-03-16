package com.example.myrestaurantapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrestaurantapp.R
import com.example.myrestaurantapp.databinding.ItemviewRecipecategoryBinding

class RecipeCategoryAdapter(
	private val recipeCategoryList: List<Pair<String, Int>>,
	private val listener: ClickListener
) : RecyclerView.Adapter<RecipeCategoryAdapter.RecipeCategoryViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoryViewHolder {
		val binding = ItemviewRecipecategoryBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return RecipeCategoryViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {
		val currentItem = recipeCategoryList[position]
		holder.bind(currentItem)
		holder.itemView.setOnClickListener {
			listener.onItemClick(currentItem)
		}
	}
	
	override fun getItemCount(): Int = recipeCategoryList.size
	
	inner class RecipeCategoryViewHolder(private val binding: ItemviewRecipecategoryBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: Pair<String, Int>) {
			binding.apply {
				Glide.with(itemView)
					.load(item.second)
					.error(R.drawable.emptyimage)
					.into(imageView)
				
				textView.text = item.first
			}
		}
	}
	
	interface ClickListener {
		fun onItemClick(item: Pair<String, Int>)
	}
}
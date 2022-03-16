package com.example.myrestaurantapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.myrestaurantapp.data.Recipes


class Comparator : DiffUtil.ItemCallback<Recipes>() {
	override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean =
		oldItem.id == newItem.id
	
	override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean =
		oldItem == newItem
}
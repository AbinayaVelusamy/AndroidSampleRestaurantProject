package com.example.myrestaurantapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrestaurantapp.R
import com.example.myrestaurantapp.data.Recipes
import com.example.myrestaurantapp.databinding.ItemviewCategorydetailsBinding

class RecipeCategoryDetailsAdapter(private val listener: OnRecipeClicked) :
	ListAdapter<Recipes, RecipeCategoryDetailsAdapter.RecipeCategoryDetailsViewHolder>(Comparator()) {
	
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RecipeCategoryDetailsViewHolder {
		val binding = ItemviewCategorydetailsBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return RecipeCategoryDetailsViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder: RecipeCategoryDetailsViewHolder, position: Int) {
		val currentItem = getItem(position)
		holder.bind(currentItem)
		holder.itemView.setOnClickListener {
			listener.onRecipeClicked(currentItem)
		}
	}
	
	class RecipeCategoryDetailsViewHolder(private val binding: ItemviewCategorydetailsBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(currentItem: Recipes) {
			binding.apply {
				Glide.with(itemView).load(currentItem.image)
					.error(R.drawable.emptyimage)
					.into(imageviewRecipe)
				textviewRecipeTitle.text = currentItem.title
			}
		}
		
	}
	
	interface OnRecipeClicked {
		fun onRecipeClicked(recipe: Recipes)
	}
}
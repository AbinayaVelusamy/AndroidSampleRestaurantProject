package com.example.myrestaurantapp.ui.details


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.myrestaurantapp.databinding.ActivityRecipeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetails : AppCompatActivity() {
	
	private val viewModel: RecipeDetailsViewModel by viewModels()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val id = intent.getIntExtra("id", 0)
		binding.apply {
			progressCircular.isVisible = true
			imageviewRecipe.isVisible = false
			textviewTitle.isVisible = false
			textviewSummary.isVisible = false
			textviewError.isVisible = false
		}
		
		viewModel.triggerRecipeDetailsById(id)
		viewModel.recipeDetails.observe(this, {
			if (it != null) {
				binding.apply {
					Glide.with(this@RecipeDetails)
						.load(it[0].image)
						.into(imageviewRecipe)
					textviewTitle.text = it[0].title
					textviewSummary.text = it[0].summary
					textviewError.isVisible = false
				}
			} else {
				binding.textviewError.isVisible = true
			}
		})
		
		viewModel.apiFlag.observe(this, {
			if (it == true) {
				binding.progressCircular.isVisible = false
				binding.imageviewRecipe.isVisible = true
				binding.textviewTitle.isVisible = true
				binding.textviewSummary.isVisible = true
			} else {
				binding.progressCircular.isVisible = false
			}
		})
		
		
	}
}
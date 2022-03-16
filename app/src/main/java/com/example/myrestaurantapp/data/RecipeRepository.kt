package com.example.myrestaurantapp.data

import com.example.myrestaurantapp.api.RecipesApi
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipesApi: RecipesApi) {
	
	suspend fun getRecipeCategoryResponse(category: String) =
		recipesApi.getRecipesByCategory(category)
	
	
	suspend fun getRecipeCategoryDetailsByID(id: Int) =
		recipesApi.getRecipeDetailsById(id)
	
}
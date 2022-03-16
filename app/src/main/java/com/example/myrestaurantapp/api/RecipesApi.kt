package com.example.myrestaurantapp.api


import com.example.myrestaurantapp.data.RecipeDetailsData
import com.example.myrestaurantapp.data.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {
	
	companion object {
		private const val API_KEY = "f8c4ceb81fee40afa9e7284905d6feb3"
	}
	
	@GET("/recipes/complexSearch/")
	suspend fun getRecipesByCategory(
		@Query("query") query: String,
		@Query("instructionsRequired") instructions: Boolean = true,
		@Query("sort") sort: String = "calories",
		@Query("sortDirection") direction: String = "asc",
		@Query("apiKey") apiKey: String = API_KEY
	): Response<Results>
	
	@GET("/recipes/informationBulk/")
	suspend fun getRecipeDetailsById(
		@Query("ids") id: Int,
		@Query("includeNutrition") includeNutrition: Boolean = false,
		@Query("apiKey") apiKey: String = API_KEY
	): Response<ArrayList<RecipeDetailsData>>
	
	
}
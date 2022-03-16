package com.example.myrestaurantapp.di

import com.example.myrestaurantapp.api.RecipesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	
	private const val BASE_URL = "https://api.spoonacular.com/"
	
	
	@Provides
	@Singleton
	fun getRetrofitInstance(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	
	@Provides
	@Singleton
	fun createRecipeApi(retrofit: Retrofit): RecipesApi {
		return retrofit.create(RecipesApi::class.java)
	}
}
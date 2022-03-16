package com.example.myrestaurantapp.data

data class Results(
	val results: List<Recipes>
)

data class Recipes(
	val id: Int,
	val image: String,
	val title: String
)




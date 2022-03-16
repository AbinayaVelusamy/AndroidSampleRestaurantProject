package com.example.myrestaurantapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myrestaurantapp.R
import com.example.myrestaurantapp.databinding.ActivityMainBinding
import com.example.myrestaurantapp.utils.RecipeCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var recipeCategories: HashMap<String, Int>
	private lateinit var recipeAdapter: RecipeCategoryAdapter
	private lateinit var gridLayoutManager: GridLayoutManager
	private lateinit var recipeCategoryFragment: RecipeCategoryFragment
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		recipeCategoryFragment = RecipeCategoryFragment()
		supportFragmentManager.beginTransaction()
			.add(R.id.fragment_container, recipeCategoryFragment, "recipe_category_fragment")
			.commit()
		selectFragment(recipeCategoryFragment)
		
		supportActionBar?.hide()
		
		recipeCategories = HashMap()
		recipeCategories["Burger"] = R.drawable.burger
		recipeCategories["Pizza"] = R.drawable.pizza
		recipeCategories["Pasta"] = R.drawable.pasta
		recipeCategories["Fries"] = R.drawable.fries
		recipeAdapter = RecipeCategoryAdapter(recipeCategories.toList(), recipeCategoryFragment)
		
		binding.apply {
			recyclerviewRecipeCategory.setHasFixedSize(true)
			recyclerviewRecipeCategory.adapter = recipeAdapter
			gridLayoutManager =
				GridLayoutManager(applicationContext, 1, GridLayoutManager.HORIZONTAL, false)
			recyclerviewRecipeCategory.layoutManager = gridLayoutManager
		}
	}
	
	private fun selectFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction().attach(fragment).commit()
	}
	
}
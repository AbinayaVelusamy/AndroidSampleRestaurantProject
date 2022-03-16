package com.example.myrestaurantapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrestaurantapp.R
import com.example.myrestaurantapp.data.Recipes
import com.example.myrestaurantapp.databinding.FragmentRecipecategorylistBinding
import com.example.myrestaurantapp.ui.details.RecipeDetails
import com.example.myrestaurantapp.utils.RecipeCategoryAdapter
import com.example.myrestaurantapp.utils.RecipeCategoryDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeCategoryFragment : Fragment(R.layout.fragment_recipecategorylist),
	RecipeCategoryAdapter.ClickListener, RecipeCategoryDetailsAdapter.OnRecipeClicked {
	private lateinit var recipeCategoryDetailsAdapter: RecipeCategoryDetailsAdapter
	private val viewModel: FragmentViewModel by viewModels()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = FragmentRecipecategorylistBinding.bind(view)
		recipeCategoryDetailsAdapter = RecipeCategoryDetailsAdapter(this)
		
		binding.apply {
			recyclerviewFragment.setHasFixedSize(true)
			recyclerviewFragment.adapter = recipeCategoryDetailsAdapter
			recyclerviewFragment.layoutManager =
				LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		}
		
		viewModel.results.observe(viewLifecycleOwner, {
			if (it != null) {
				binding.recyclerviewFragment.isVisible = true
				recipeCategoryDetailsAdapter.submitList(it)
				binding.textviewError.isVisible = false
			} else {
				binding.recyclerviewFragment.isVisible = false
				binding.textviewError.isVisible = true
			}
		})
		
		viewModel._query.observe(this, {
			viewModel.triggerRecipeApi()
		})
	}
	
	override fun onItemClick(item: Pair<String, Int>) {
		viewModel.onQueryChanged(item.first)
	}
	
	override fun onRecipeClicked(recipe: Recipes) {
		/* val directions = RecipeCategoryFragmentDirections.actionRecipeCategoryFragmentToRecipeDetails(recipe.id)
		 findNavController().navigate(directions)*/
		val intent = Intent(requireActivity(), RecipeDetails::class.java)
		intent.putExtra("id", recipe.id)
		startActivity(intent)
	}
}
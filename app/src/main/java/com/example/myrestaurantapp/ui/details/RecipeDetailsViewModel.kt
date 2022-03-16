package com.example.myrestaurantapp.ui.details


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrestaurantapp.data.RecipeDetailsData
import com.example.myrestaurantapp.data.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val repository: RecipeRepository) :
	ViewModel() {
	var recipeDetails = MutableLiveData<ArrayList<RecipeDetailsData>?>()
	var apiFlag = MutableLiveData<Boolean>()
	
	fun triggerRecipeDetailsById(id: Int) {
		
		viewModelScope.launch {
			repository.getRecipeCategoryDetailsByID(id).let { response ->
				if (response.isSuccessful) {
					recipeDetails.postValue(response.body())
					apiFlag.value = true
				} else {
					recipeDetails.postValue(null)
					apiFlag.value = false
				}
				
			}
			
		}
	}
	
}
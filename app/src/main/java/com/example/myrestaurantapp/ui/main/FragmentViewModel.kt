package com.example.myrestaurantapp.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrestaurantapp.data.RecipeRepository
import com.example.myrestaurantapp.data.Recipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentViewModel @Inject constructor(private val repository: RecipeRepository) :
	ViewModel() {
	
	var results = MutableLiveData<List<Recipes>?>()
	var _query = MutableLiveData<String>()
	
	init {
		_query.value = "Burger"
	}
	
	
	fun onQueryChanged(category: String) {
		_query.value = category
	}
	
	fun triggerRecipeApi() =
		viewModelScope.launch {
			repository.getRecipeCategoryResponse(_query.value!!).let { response ->
				if (response.isSuccessful) {
					results.postValue(response.body()?.results)
				} else {
					results.postValue(null)
				}
			}
		}
}
package com.D121211097.task_app.ui.activities.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211097.taskapp.MyApplication
import com.D121211097.taskapp.data.models.Food
import com.D121211097.taskapp.data.repository.FoodRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed interface MainUiState{
    data class Success(val Food: List<Food>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}
class ListViewModel(private val FoodRepository : FoodRepository): ViewModel() {

    var mainUiState : MainUiState by mutableStateOf(MainUiState.Loading)
        private set
    fun getFood() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try{
            val result = FoodRepository.getFood()
            mainUiState = MainUiState.Success(result.categories.orEmpty())
        } catch (e: IOException){
            mainUiState = MainUiState.Error
        }
    }
    init {
        getFood()
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val repository = application.container.foodRepository
                ListViewModel(repository)
            }
        }
    }
}
package com.D121211097.taskapp.data.repository

import com.D121211097.taskapp.data.response.GetFoodResponse
import com.D121211097.taskapp.data.source.ApiService

class FoodRepository(private val apiService: ApiService) {
    suspend fun getFood(): GetFoodResponse {
        return apiService.getFood()
    }
}
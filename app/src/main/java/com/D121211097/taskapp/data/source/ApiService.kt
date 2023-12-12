package com.D121211097.taskapp.data.source

import com.D121211097.taskapp.data.response.GetFoodResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getFood(
    ): GetFoodResponse
}
package com.D121211097.taskapp.data.response

import com.D121211097.taskapp.data.models.Food
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFoodResponse(
    @SerialName("categories")
    val categories: List<Food>
)
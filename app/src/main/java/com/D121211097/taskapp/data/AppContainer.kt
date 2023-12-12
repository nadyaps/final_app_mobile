package com.D121211097.taskapp.data

import com.D121211097.taskapp.data.repository.FoodRepository
import com.D121211097.taskapp.data.source.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val foodRepository : FoodRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.themealdb.com/"

    //inisialisasi Retrofit
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // Inisialisasi ApiService menggunakan Retrofit
    private val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

    // Inisialisasi EarthquakeRepository
    override val foodRepository: FoodRepository
        get() = FoodRepository(retrofitService)
}
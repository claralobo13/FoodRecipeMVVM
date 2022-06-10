package com.example.foodrecipe.network

import com.example.foodrecipe.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {


    @GET("RecipeResponse.json")

    suspend fun getRecipes(
        @Query("apiKey") api_key: String?,
        @Query("number") number: Int
    ): RecyclerList

}

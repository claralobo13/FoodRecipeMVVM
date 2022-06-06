package com.example.foodrecipe.network

import com.example.foodrecipe.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServices {
    //using coroutine so suspend function

    @GET("recipes/complexSearch")

    suspend fun getRecipes(
        @Query("apiKey") api_key: String?,
        @Query("number") number: Int
    ): RecyclerList



}
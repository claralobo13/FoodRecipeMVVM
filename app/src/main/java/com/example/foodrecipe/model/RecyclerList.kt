package com.example.foodrecipe.model


//Kotlin Data class for JSON response of Recipe
data class RecyclerList(val results: ArrayList<RecyclerData>)
data class RecyclerData(val title: String, val image: String)

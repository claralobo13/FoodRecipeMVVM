package com.example.foodrecipe.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.foodrecipe.model.RecyclerList
import com.example.foodrecipe.network.RetroInstance
import com.example.foodrecipe.network.RetroServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {
    lateinit var recyclerListLiveData:MutableLiveData<RecyclerList>
    //initialization
    init{
       recyclerListLiveData= MutableLiveData()
    }
// function to return livedata

    fun getRecyclerListObserver():MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    // function to make api call

    fun makeAPiCall(){
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance=RetroInstance.getRetroInstance().create(RetroServices::class.java)
            val response= retroInstance.getRecipes("91e9af87e23446fba7be44399ac65782", 2)
            recyclerListLiveData.postValue(response)

        }
    }

}
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
    var recyclerListLiveData:MutableLiveData<RecyclerList> = MutableLiveData()
    // function to return livedata

    fun getRecyclerListObserver():MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    // function to make api call

    fun makeAPiCall(){
        //below is the function for coroutine
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance=RetroInstance.getRetroInstance().create(RetroServices::class.java)
            val response= retroInstance.getRecipes("91e9af87e23446fba7be44399ac65782", 100)
            recyclerListLiveData.postValue(response)

        }
    }

}
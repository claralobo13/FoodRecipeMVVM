package com.example.foodrecipe.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.foodrecipe.model.RecyclerList
import com.example.foodrecipe.network.RetroInstance
import com.example.foodrecipe.network.RetroServices
import com.example.foodrecipe.utils.Constants.API_KEY
import com.example.foodrecipe.utils.Constants.NUMBER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// function to return livedata - getRecyclerListObserver()
// function to make api call - makeAPiCall()    includes function for coroutine


class RecipeListViewModel: ViewModel() {
    var recyclerListLiveData:MutableLiveData<RecyclerList> = MutableLiveData()


    fun getRecyclerListObserver():MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }


    fun makeAPiCall(){
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance=RetroInstance.getRetroInstance().create(RetroServices::class.java)
            val response= retroInstance.getRecipes(API_KEY, NUMBER)
            recyclerListLiveData.postValue(response)

        }
    }

}
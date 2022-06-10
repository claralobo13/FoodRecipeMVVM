package com.example.foodrecipe.viewmodel

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.foodrecipe.FileReader
import com.example.foodrecipe.getOrAwaitValue
import com.example.foodrecipe.model.RecyclerData
import com.example.foodrecipe.model.RecyclerList
import com.example.foodrecipe.network.RetroInstanceTest
import com.example.foodrecipe.network.RetrofitService
import com.example.foodrecipe.utils.Constants.API_KEY
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)

class RecipeListViewModelTest {

    private val mockWebServer = MockWebServer()


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: RecipeListViewModel
    lateinit var gson: Gson

    @Mock
    lateinit var apiService: RetrofitService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainViewModel = RecipeListViewModel()
        gson= Gson()
    }

    @Test
    fun getRecyclerListLiveData() {

    }

    @Test
    fun setRecyclerListLiveData() {

      setRecyclerListLiveData()
    }

    @Test
    fun getRecyclerListObserver() {
         getRecyclerListLiveData()
    }

    @Test(timeout=10000)
    fun makeAPiCall() {
        runBlocking {
            val recyclerData= RecyclerData("716426","https://spoonacular.com/recipeImages/715594-312x231.jpg")
             val items=  ArrayList<RecyclerData>()
            items.add(0,recyclerData)
            val recyclerList=RecyclerList(items)


            Mockito.`when`(apiService.getRecipes(API_KEY,1))
                .thenReturn(RecyclerList(items))
            mainViewModel.getRecyclerListObserver()
           mainViewModel.recyclerListLiveData.postValue(apiService.getRecipes(API_KEY,1))
            val result = mainViewModel.recyclerListLiveData.getOrAwaitValue()
            assertEquals(recyclerList,result)
        }
    }
}

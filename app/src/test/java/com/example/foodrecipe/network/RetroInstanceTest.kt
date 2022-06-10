package com.example.foodrecipe.network

import com.example.foodrecipe.utils.Constants.API_KEY
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstanceTest{
    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: RetrofitService

    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RetrofitService::class.java)
    }
    @Test
    fun `get all recipe api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getRecipes(API_KEY,1)
            val request = mockWebServer.takeRequest()
            assertEquals("/RecipeResponse.json",request.path)
            assertEquals(true, response.results.isEmpty())
        }
    }
    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}
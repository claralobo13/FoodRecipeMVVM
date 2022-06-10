package com.example.foodrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.foodrecipe.databinding.ActivityMainBinding

//using ViewBinding, create instance of it and get root view and pass it to setOnCreateView
// ViewBinding provides view to bind with the classes replacing fvByID
class FoodRecipeList : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    private fun setupFragment(){

        val fragment= RecipeListFragment.newInstance()
        val fragmentManager:FragmentManager= supportFragmentManager
        val fragmentTransaction: FragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}
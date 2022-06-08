package com.example.foodrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.foodrecipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding// ViewBinding provides view to bind with the classes replacing fvByID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //using ViewBinding, create instance of it and get root view and pass it to setOnCreateView
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    private fun setupFragment(){

        val fragment= RecyclerListFragment.newInstance()// instance of RecyclerListFragment
        val fragmentManager:FragmentManager= supportFragmentManager//instance of fragment manager
        val fragmentTransaction: FragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}